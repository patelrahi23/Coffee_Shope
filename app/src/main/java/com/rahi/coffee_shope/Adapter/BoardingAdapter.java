package com.rahi.coffee_shope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.rahi.coffee_shope.R;

public class BoardingAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    int images[]={R.drawable.onboarding_1_transparent,
            R.drawable.onboarding_2_transparent,
            R.drawable.onboarding_3_transparent};
    String headings[] = {
            "Have a black Coffee in the morning to keep your health healthy.",
            "Have Coffee in morning and wander through your city with your hand in time.",
            "Let's enjoy a chocolate cafe mocha in the morning"};

    public BoardingAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_screens,container,false);
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
