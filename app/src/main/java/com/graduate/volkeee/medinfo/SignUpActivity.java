package com.graduate.volkeee.medinfo;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.graduate.volkeee.medinfo.model.Account;
import com.graduate.volkeee.medinfo.views.NonSwipeableViewPager;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity
        implements BasicInfoSignUpFragment.OnFragmentInteractionListener,
        AdditionalInfoSignUpFragment.OnFragmentInteractionListener,
        PasswordSignUpFragment.OnFragmentInteractionListener {
    public Account mAccount;
    @ViewById(R.id.user_profile_picture)
    CircularImageView profileImage;

    CustomPagerAdapter mViewPagerAdapter;

    @ViewById(R.id.sign_up_main_content)
    ViewGroup transitionsContainer;

    @ViewById(R.id.viewPager)
    NonSwipeableViewPager viewPager;

    @ViewById(R.id.button_next)
    ImageButton buttonNext;

    @ViewById(R.id.button_accept)
    ImageButton buttonAccept;

    @ViewById(R.id.button_previous)
    ImageButton buttonPrevious;

    @Pref
    AuthTokenPreferences_ authTokenPreferences;

    private RequestQueue mRequestQueue;

    @AfterViews
    void initializeViewPager() {
        viewPager.setPagingEnabled(false);
        mViewPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Here's your instance
                BasicSignUpFragment fragment = mViewPagerAdapter.getRegisteredFragment(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(2);
    }

    @AfterViews
    void initializeAccount() {
        mAccount = new Account();
    }

    @AfterViews
    void initializeRequestQueue() {
        mRequestQueue = Volley.newRequestQueue(this);
    }

    @Click(R.id.user_profile_picture)
    void profilePictureClick() {
        ImagePicker.create(this).limit(1) // Activity or Fragment
                .start();
    }

    @Click(R.id.button_next)
    void buttonNextClick() {
        BasicSignUpFragment fragment = mViewPagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
        if (fragment.validate()) {
            fragment.gatherData(mAccount);

            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (buttonPrevious.getVisibility() == View.INVISIBLE) {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Rotate())
                        .addTransition(new Scale(0.1f))
                        .setInterpolator(new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                buttonPrevious.setRotation(360);
                buttonPrevious.setVisibility(View.VISIBLE);
            }
            if (viewPager.getCurrentItem() == 2) {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Rotate())
                        .addTransition(new Scale(0.1f))
                        .setInterpolator(new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);

                buttonNext.setVisibility(View.INVISIBLE);

                buttonAccept.setVisibility(View.VISIBLE);
            }
        }
        Log.d("aaaaaa", mAccount.toString());
    }

    @Click(R.id.button_previous)
    void buttonPreviousClick() {
        if (viewPager.getCurrentItem() == 2) {
            TransitionSet set = new TransitionSet()
                    .addTransition(new Rotate())
                    .addTransition(new Scale(0.1f))
                    .setInterpolator(new FastOutLinearInInterpolator());
            TransitionManager.beginDelayedTransition(transitionsContainer, set);

            buttonAccept.setVisibility(View.INVISIBLE);
            buttonNext.setVisibility(View.VISIBLE);
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        if (viewPager.getCurrentItem() == 0) {
            TransitionManager.beginDelayedTransition(transitionsContainer);
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
    }

    @Click(R.id.button_accept)
    void buttonAcceptClick() {
        BasicSignUpFragment fragment = mViewPagerAdapter.getRegisteredFragment(viewPager.getCurrentItem());
        if (fragment.validate()) {
            fragment.gatherData(mAccount);
        }
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://192.168.1.2:881/account/register",
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        if (!jsonResponse.has("error_code")) {
                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Log.e("Register error", error.toString());
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(Account.KEY_firstName, mAccount.getFirstName());
                params.put(Account.KEY_lastName, mAccount.getLastName());
                params.put(Account.KEY_midName, mAccount.getMidName());
                params.put(Account.KEY_email, mAccount.getEmail());
                params.put(Account.KEY_password, mAccount.getPassword());
                params.put(Account.KEY_adress, mAccount.getAdress());
                params.put(Account.KEY_dateOfBirth, mAccount.getDateOfBirth());
                params.put(Account.KEY_phone, mAccount.getPhone());
                params.put(Account.KEY_sex, mAccount.getSex());

                return params;
            }
        };
        mRequestQueue.add(request);
    }

    public void sendAuthRequest(Account account) {
        StringRequest request = new StringRequest(Request.Method.POST,
                "\"http://192.168.1.2:881/account/auth",
                response -> {

                }, error -> {

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put(Account.KEY_email, account.getEmail());
                params.put(Account.KEY_password, account.getPassword());

                return params;
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityHelper.overridePendingTransitionExit(this);
    }

    @Click(R.id.button_cancel)
    void onBackClick() {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);

            Log.i("Selected image path", image.getPath());

            File file = new File(image.getPath());

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class CustomPagerAdapter extends FragmentStatePagerAdapter {
        SparseArray<BasicSignUpFragment> registeredFragments = new SparseArray<>();
        private final Integer NUM_ITEMS = 3;
        private int currentPosition;

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BasicSignUpFragment getItem(int position) {
            switch (position) {
                case 0:
                    currentPosition = 0;
                    return BasicInfoSignUpFragment_.builder().build();
                case 1:
                    currentPosition = 1;
                    return AdditionalInfoSignUpFragment_.builder().build();
                case 2:
                    currentPosition = 2;
                    return PasswordSignUpFragment_.builder().build();
            }
            return null;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasicSignUpFragment fragment = (BasicSignUpFragment) super.instantiateItem(container, position);
            registeredFragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public BasicSignUpFragment getRegisteredFragment(int position) {
            return registeredFragments.get(position);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        public int getCurrentItemPosition() {
            return currentPosition;
        }
    }

}
