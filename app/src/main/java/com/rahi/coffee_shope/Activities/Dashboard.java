package com.rahi.coffee_shope.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahi.coffee_shope.Fragments.Fragment_Account;
import com.rahi.coffee_shope.Fragments.Fragment_Cart;
import com.rahi.coffee_shope.Fragments.Fragment_Home;
import com.rahi.coffee_shope.Fragments.Fragment_Wishlist;
import com.rahi.coffee_shope.R;

public class Dashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    Fragment Selected_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(Dashboard.this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int itemId = item.getItemId();
        if (itemId == R.id.nav_home) {
            Selected_fragment = new Fragment_Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Selected_fragment).commit();
//            bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(Color.parseColor("#ff000000")));
        } else if (itemId == R.id.nav_account) {
            Selected_fragment = new Fragment_Account();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Selected_fragment).commit();
        } else if(itemId == R.id.nav_cart){
            Selected_fragment = new Fragment_Cart();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Selected_fragment).commit();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = getWindow().getDecorView();
        int UIView = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(UIView);

        Selected_fragment = new Fragment_Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Selected_fragment).commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View view = getWindow().getDecorView();
        int UIView = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(UIView);

//        Selected_fragment = new Fragment_Home();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Selected_fragment).commit();
    }
}