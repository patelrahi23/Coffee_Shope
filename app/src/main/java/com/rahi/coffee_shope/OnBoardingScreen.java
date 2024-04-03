package com.rahi.coffee_shope;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rahi.coffee_shope.Activities.Dashboard;
import com.rahi.coffee_shope.Adapter.BoardingAdapter;

public class OnBoardingScreen extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button start;
    FloatingActionButton fab;
    TextView[] dots;
    BoardingAdapter sliderAdapter;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        viewPager = findViewById(R.id.viewpager);
        start = findViewById(R.id.button_start);
        fab = findViewById(R.id.fab_next);
        dotsLayout = findViewById(R.id.dots);
        sliderAdapter = new BoardingAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        fab.setOnClickListener(this::next);
        start.setOnClickListener(view -> {
            Intent intent = new Intent(OnBoardingScreen.this, Dashboard.class);
            startActivity(intent);
            finish();
        });

    }
    public void next(View view){
        viewPager.setCurrentItem(currentPosition+1);
    }

    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.md_brown_800));
        }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition=position;
            if(position==0){
                fab.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
            }
            else if (position==1){
                fab.setVisibility(View.VISIBLE);
                start.setVisibility(View.GONE);
            }
            else {
                fab.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        View view = getWindow().getDecorView();
        int UIView = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(UIView);
    }

}