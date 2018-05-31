package com.graduate.volkeee.medinfo;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.APPLICATION_DEFAULT)
public interface AuthTokenPreferences {
        String authToken();

        long lastUpdated();
}
