package com.graduate.volkeee.medinfo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.graduate.volkeee.medinfo.model.Account;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EFragment(R.layout.fragment_additional_info_sign_up)
public class AdditionalInfoSignUpFragment extends BasicSignUpFragment implements DatePickerDialog.OnDateSetListener {
    @ViewById(R.id.textInputDate)
    TextInputLayout textInputLayoutDate;

    @ViewById(R.id.textInputPhone)
    TextInputLayout textInputLayoutPhone;

    @ViewById(R.id.textInputAddress)
    TextInputLayout textInputLayoutAddress;

    @ViewById(R.id.spinner)
    MaterialSpinner spinner;

    @AfterViews
    void setListenersOnTextInputs() {
        textInputLayoutDate.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutDate.setErrorEnabled(false);
                textInputLayoutDate.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textInputLayoutPhone.getEditText().addTextChangedListener(new MaskedTextChangedListener("+38(0[00])[00]-[00]-[000]",
                textInputLayoutPhone.getEditText(),
                (b, s) -> {

                }) );
        textInputLayoutAddress.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutAddress.setErrorEnabled(false);
                textInputLayoutAddress.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private DatePickerDialog mDatePickerDialog;

    @AfterViews
    void initializeCalendar() {
        Calendar calendar = Calendar.getInstance();
        mDatePickerDialog = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        mDatePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);

        textInputLayoutDate.getEditText().setOnClickListener(view -> {
            mDatePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
        });
    }

    @AfterViews
    void initializeSpinner() {
        spinner.setItems(getString(R.string.sex_male), getString(R.string.sex_female));
    }

    private OnFragmentInteractionListener mListener;

    public AdditionalInfoSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void gatherData(Account account) {
        account.setDateOfBirth(textInputLayoutDate.getEditText().getText().toString());

        String phone = textInputLayoutPhone.getEditText().getText().toString();
        String regx = "()-+";
        char[] ca = regx.toCharArray();
        for (char c : ca) {
            phone = phone.replace(""+c, "");
        }
        account.setPhone(phone);
        account.setAdress(textInputLayoutAddress.getEditText().getText().toString());


        account.setSex(spinner.getItems().get(spinner.getSelectedIndex()).toString().toLowerCase());
    }

    @Override
    public boolean validate() {
        String address = textInputLayoutAddress.getEditText().getText().toString();

        resetErrors();

        if(validateAddress(address)) {
            return true;
        } else {
            textInputLayoutAddress.setErrorEnabled(true);
            textInputLayoutAddress.setError(getString(R.string.error_invalid_address));
        }
        return false;
    }

    boolean validateAddress(String address) {
        Pattern p = Pattern.compile(".{3,30}, .{1,3}, .{3,30}$");
        Matcher m = p.matcher(address);
        return m.matches();
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.UK);
        dateFormat.setLenient(false);
        String dateFormatted;

        Activity activity = getActivity();
        if(activity != null && activity instanceof SignUpActivity)
        try {
            Date date = dateFormat.parse(String.valueOf(year) + "/" + String.valueOf(monthOfYear + 1) + "/" +  String.valueOf(dayOfMonth));
            dateFormatted = dateFormat.format(date);
            ((SignUpActivity)activity).mAccount.setDateOfBirth(dateFormatted);

            textInputLayoutDate.getEditText().setText(dateFormatted);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void resetErrors() {
        textInputLayoutAddress.setError(null);
        textInputLayoutAddress.setErrorEnabled(false);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
