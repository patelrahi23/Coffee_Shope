package com.rahi.coffee_shope.Fragments;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahi.coffee_shope.Adapter.CoffeeAdapter;
import com.rahi.coffee_shope.POJO.CoffeeModelClass;
import com.rahi.coffee_shope.R;

import java.util.ArrayList;


public class Fragment_Home extends Fragment {

    RecyclerView recyclerView;
    ArrayList<CoffeeModelClass> coffeeModelClasses;
    CoffeeAdapter adapter;
    ScrollView scroll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__home,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        scroll = view.findViewById(R.id.scroll);

        scroll.setVerticalScrollBarEnabled(false);
        scroll.setHorizontalScrollBarEnabled(false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        coffeeModelClasses = new ArrayList<>();

        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_iced_coffee,50.00,"Iced Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_cappuccino,80.00,"Latte Macchiato"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_black_coffee,90.00,"Black Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_iced_coffee,50.00,"Iced Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_cappuccino,80.00,"Latte Macchiato"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_black_coffee,90.00,"Black Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_iced_coffee,50.00,"Iced Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_cappuccino,80.00,"Latte Macchiato"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_black_coffee,90.00,"Black Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_iced_coffee,50.00,"Iced Coffee"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_cappuccino,80.00,"Latte Macchiato"));
        coffeeModelClasses.add(new CoffeeModelClass(R.drawable.ic_black_coffee,90.00,"Black Coffee"));

        adapter = new CoffeeAdapter(coffeeModelClasses,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        View view = getActivity().getWindow().getDecorView();
        int UIView = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        view.setSystemUiVisibility(UIView);
    }


}