package com.graduate.volkeee.medinfo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity {
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @AfterViews
    void initializeToolbar() {
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
