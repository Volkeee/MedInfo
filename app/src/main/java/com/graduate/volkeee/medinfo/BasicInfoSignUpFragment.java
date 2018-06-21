package com.graduate.volkeee.medinfo;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

import com.graduate.volkeee.medinfo.model.Account;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EFragment(R.layout.fragment_basic_info_sign_up)
public class BasicInfoSignUpFragment extends BasicSignUpFragment {
    @ViewById(R.id.textInputEmail)
    TextInputLayout textInputLayoutEmail;

    @ViewById(R.id.textInputName)
    TextInputLayout textInputLayoutName;

    @ViewById(R.id.textInputSurname)
    TextInputLayout textInputLayoutSurname;

    @ViewById(R.id.textInputMiddlename)
    TextInputLayout textInputLayoutMiddlename;

    @AfterViews
    void setListenersOnTextInputs() {
        textInputLayoutName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutName.setErrorEnabled(false);
                textInputLayoutName.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textInputLayoutEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutEmail.setErrorEnabled(false);
                textInputLayoutEmail.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textInputLayoutSurname.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutSurname.setErrorEnabled(false);
                textInputLayoutSurname.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textInputLayoutMiddlename.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                textInputLayoutMiddlename.setErrorEnabled(false);
                textInputLayoutMiddlename.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private OnFragmentInteractionListener mListener;

    public BasicInfoSignUpFragment() {
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
        resetErrors();
        account.setEmail(textInputLayoutEmail.getEditText().getText().toString());
        account.setFirstName(textInputLayoutName.getEditText().getText().toString());
        account.setLastName(textInputLayoutSurname.getEditText().getText().toString());
        account.setMidName(textInputLayoutMiddlename.getEditText().getText().toString());
    }

    @Override
    public boolean validate() {
        String email = textInputLayoutEmail.getEditText().getText().toString().trim();
        String name = textInputLayoutName.getEditText().getText().toString().trim();
        String surname = textInputLayoutSurname.getEditText().getText().toString().trim();
        String middlename = textInputLayoutMiddlename.getEditText().getText().toString().trim();

        Integer matchCounter = 0;

        resetErrors();

        if(validateEmail(email)) {
            matchCounter++;
        } else {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError(getString(R.string.error_invalid_email));
        }

        if(validateName(name)) {
            matchCounter++;
        } else {
            textInputLayoutName.setErrorEnabled(true);
            textInputLayoutName.setError(getString(R.string.error_wrong_name));
        }
        if(validateName(surname)) {
            matchCounter++;
        } else {
            textInputLayoutSurname.setErrorEnabled(true);
            textInputLayoutSurname.setError(getString(R.string.error_wrong_name));
        }
        if(validateName(middlename)) {
            matchCounter++;
        } else {
            textInputLayoutMiddlename.setErrorEnabled(true);
            textInputLayoutMiddlename.setError(getString(R.string.error_wrong_name));
        }
        return (matchCounter == 4);
    }

    boolean validateEmail(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    boolean validateName(String name) {
        Pattern p = Pattern.compile("^[a-zA-Zа-яА-ЯіІїЇЬь']{3,50}$");
        Matcher m = p.matcher(name);
        return m.matches();
    }

    void resetErrors() {
        textInputLayoutEmail.setError(null);
        textInputLayoutEmail.setErrorEnabled(false);
        textInputLayoutName.setError(null);
        textInputLayoutName.setErrorEnabled(false);
        textInputLayoutSurname.setError(null);
        textInputLayoutSurname.setErrorEnabled(false);
        textInputLayoutMiddlename.setError(null);
        textInputLayoutMiddlename.setErrorEnabled(false);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
