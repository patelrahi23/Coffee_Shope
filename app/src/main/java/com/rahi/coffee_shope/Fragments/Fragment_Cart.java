package com.rahi.coffee_shope.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahi.coffee_shope.Adapter.CartAdapter;
import com.rahi.coffee_shope.Database.DatabaseHelper;
import com.rahi.coffee_shope.POJO.CartModelClass;
import com.rahi.coffee_shope.R;

import java.util.ArrayList;

public class Fragment_Cart extends Fragment {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<CartModelClass> cartModelClasses;
    String title,size;
    double price;
    int counter;
    LinearLayoutManager llm;
    DatabaseHelper databaseHelper;

    public Fragment_Cart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart,container,false);

        recyclerView = view.findViewById(R.id.cartrecyclerview);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerview();

        return view;
    }

    private void recyclerview() {
        cartModelClasses = new ArrayList<>();
        cartAdapter = new CartAdapter(cartModelClasses,getActivity());
        recyclerView.setAdapter(cartAdapter);

        databaseHelper = new DatabaseHelper(getActivity());
        Cursor cursor = databaseHelper.getData();
        while (cursor.moveToNext()){
            title =cursor.getString(0);
            price = cursor.getDouble(1);
            counter = cursor.getInt(2);
            size = cursor.getString(3);
            cartModelClasses.add(new CartModelClass(title,counter,price,size));
        }
        cursor.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        cartAdapter.notifyDataSetChanged();
    }
}