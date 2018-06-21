package com.graduate.volkeee.medinfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduate.volkeee.medinfo.model.Account;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EFragment(R.layout.fragment_password_sign_up)
public class PasswordSignUpFragment extends BasicSignUpFragment {
    @ViewById(R.id.textInputLayoutPasswordMain)
    TextInputLayout textInputLayoutPasswordMain;

    @ViewById(R.id.textInputLayoutPasswordRepeat)
    TextInputLayout textInputLayoutPasswordRepeat;

    private OnFragmentInteractionListener mListener;

    public PasswordSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_sign_up, container, false);
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
        account.setPassword(textInputLayoutPasswordMain.getEditText().getText().toString());
    }

    @Override
    public boolean validate() {
        textInputLayoutPasswordMain.setError(null);
        textInputLayoutPasswordRepeat.setError(null);

        textInputLayoutPasswordMain.setErrorEnabled(false);
        textInputLayoutPasswordRepeat.setErrorEnabled(false);

        String mainPassword = textInputLayoutPasswordMain.getEditText().getText().toString();
        String repeatedPassword = textInputLayoutPasswordRepeat.getEditText().getText().toString();
        if(mainPassword.equals(repeatedPassword)) {
            Pattern p = Pattern.compile(".{6,64}$");
            Matcher m = p.matcher(textInputLayoutPasswordMain.getEditText().getText().toString());
            if(!m.matches()) {
                textInputLayoutPasswordMain.setErrorEnabled(true);
                textInputLayoutPasswordRepeat.setError(getString(R.string.error_invalid_password));

                return false;
            } else return true;
        } else {
            textInputLayoutPasswordMain.setErrorEnabled(true);
            textInputLayoutPasswordRepeat.setError(getString(R.string.error_password_dont_match));
            return false;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
