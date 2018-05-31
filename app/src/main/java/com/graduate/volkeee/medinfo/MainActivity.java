package com.graduate.volkeee.medinfo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mikhaellopez.circularimageview.CircularImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.fab)
    FloatingActionButton floatingActionButton;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.user_profile_picture)
    CircularImageView userProfilePicture;

    @Pref
    AuthTokenPreferences_ authTokenPreferences;

    void userProfilePictureClick() {
        String token = authTokenPreferences.authToken().get();
        if(authTokenPreferences.authToken().exists()) {
            Toast.makeText(this, "Logged in with " + authTokenPreferences.authToken().get() + "token", Toast.LENGTH_SHORT).show();
        } else {
            LoginActivity_.intent(this).start();
            ActivityHelper.overridePendingTransitionEnter(this);
        }
    }

    @AfterViews
    void setDrawer() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getHeaderView(0).findViewById(R.id.user_profile_picture).setOnClickListener(view -> userProfilePictureClick());
    }

    @OptionsItem(R.id.action_authorize)
    void authorizeSelected() {
        LoginActivity_.intent(this).start();
        ActivityHelper.overridePendingTransitionEnter(this);
    }

    @Click(R.id.fab)
    void fabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_camera:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
