package com.graduate.volkeee.medinfo;

import android.app.Activity;

public class ActivityHelper {
    public static void overridePendingTransitionEnter(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public static void overridePendingTransitionExit(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
