package com.graduate.volkeee.medinfo;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    private final String authorizationURL = "http://31.134.70.105:811/account/auth";
    private RequestQueue mRequestQueue;
    @ViewById(R.id.text_input_email)
    TextInputEditText textInputEmail;

    @ViewById(R.id.text_input_password)
    TextInputEditText textInputPassword;

    @ViewById(R.id.button_sign_in)
    Button buttonSignIn;

    @ViewById(R.id.button_sign_up)
    Button buttonSignUp;

    @Pref
    AuthTokenPreferences_ authTokenPreferences;

    @AfterViews
    void initializeRequestQueue() {
        mRequestQueue = Volley.newRequestQueue(this);
    }

    @Click(R.id.button_sign_in)
    void signInButtonClick(View view) {
        String email = textInputEmail.getText().toString();
        String password = textInputPassword.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()) {
            authorizeRequest(email, password);
        }
    }

    @Click(R.id.button_sign_up)
    void signUpButtonClick(View view) {
        SignUpActivity_.intent(this).start();
        ActivityHelper.overridePendingTransitionEnter(this);
    }

    @Background
    void authorizeRequest(String email, String password) {
        StringRequest authorizationRequest = new StringRequest(Request.Method.POST, authorizationURL, response -> {
                    Log.d("Auth token", response);
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (handleResponse(response)) {
                            authTokenReceived(jsonResponse);
                        } else
                            authError(jsonResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
        }, error -> {
                    Log.e("Auth Error", "Authorization error:\n" + error);
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();

                params.put("passwd", password);
                params.put("email", email);

                return params;
            }
        };
        mRequestQueue.add(authorizationRequest);
    }

    boolean handleResponse(String string) {
        try {
            JSONObject response = new JSONObject(string);

            return !response.has("error_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @UiThread
    void authTokenReceived(JSONObject jsonObject) {
        try {
            String key = jsonObject.getString("access_token");
            authTokenPreferences.edit().authToken().put(key).apply();

            Toast.makeText(this, "Succesfully logged in as ", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @UiThread
    void authError(JSONObject jsonObject) {
        try {
            Toast.makeText(this, jsonObject.get("error_msg").toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityHelper.overridePendingTransitionExit(this);
    }

    @OptionsItem(android.R.id.home)
    void onBackClick() {
        onBackPressed();
    }
}
