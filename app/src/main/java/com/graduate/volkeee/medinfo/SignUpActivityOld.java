package com.graduate.volkeee.medinfo;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikhaellopez.circularimageview.CircularImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

//@EActivity(R.layout.activity_sign_up)
public class SignUpActivityOld extends AppCompatActivity {
//    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
//    private boolean mIsAvatarShown = true;
//    private int mMaxScrollSize;
//
//    @ViewById(R.id.materialup_appbar)
//    AppBarLayout appbarLayout;
//
//    @ViewById(R.id.materialup_profile_image)
//    CircularImageView profileImage;
//
//    @ViewById(R.id.materialup_toolbar)
//    Toolbar toolbar;
//
//
//    @AfterViews
//    void afterViews() {
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());
//
//        appbarLayout.addOnOffsetChangedListener((layout, integer) -> {
//            if (mMaxScrollSize == 0)
//                mMaxScrollSize = appbarLayout.getTotalScrollRange();
//
//            int percentage = (Math.abs(integer)) * 100 / mMaxScrollSize;
//
//            if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
//                mIsAvatarShown = false;
//
//                profileImage.animate()
//                        .scaleY(0).scaleX(0)
//                        .setDuration(200)
//                        .start();
//            }
//
//            if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
//                mIsAvatarShown = true;
//
//                profileImage.animate()
//                        .scaleY(1).scaleX(1)
//                        .start();
//            }
//        });
//        mMaxScrollSize = appbarLayout.getTotalScrollRange();
//    }
}