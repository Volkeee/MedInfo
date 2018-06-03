package com.graduate.volkeee.medinfo;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.graduate.volkeee.medinfo.model.Account;
import com.graduate.volkeee.medinfo.views.NonSwipeableViewPager;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity implements BasicInfoSignUpFragment.OnFragmentInteractionListener, AdditionalInfoSignUpFragment.OnFragmentInteractionListener {
    protected Account mAccount;
    @ViewById(R.id.user_profile_picture)
    CircularImageView profileImage;

    CustomPagerAdapter mViewPagerAdapter;

    @ViewById(R.id.viewPager)
    NonSwipeableViewPager viewPager;

    @AfterViews
    void initializeViewPager() {
        viewPager.setPagingEnabled(false);
        mViewPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
    }

    @Click(R.id.button_next)
    void buttonNextClick() {
        viewPager.setCurrentItem(mViewPagerAdapter.getCurrentItemPosition() + 1);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class CustomPagerAdapter extends FragmentStatePagerAdapter {
        private final Integer NUM_ITEMS = 2;
        private int currentPosition;

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    currentPosition = 0;
                    return BasicInfoSignUpFragment_.builder().build();
                case 1:
                    currentPosition = 1;
                    return AdditionalInfoSignUpFragment_.builder().build();
            }
            return null;
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
