package com.rahi.coffee_shope.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.rahi.coffee_shope.Database.DatabaseHelper;
import com.rahi.coffee_shope.POJO.CoffeeModelClass;
import com.rahi.coffee_shope.R;

import java.util.ArrayList;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {

    ArrayList<CoffeeModelClass> modelClasses;
    DatabaseHelper databaseHelper;
    Context context;
    public int counter = 0;
    String size;
    Animation card_animation_fade_in,recyc_anim;
    int last_itemcount = -1;

    public CoffeeAdapter(ArrayList<CoffeeModelClass> modelClasses, Context context) {
        this.modelClasses = modelClasses;
        this.context = context;
    }

    @NonNull
    @Override
    public CoffeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeAdapter.ViewHolder holder, int position) {

        CoffeeModelClass coffeeModelClass = modelClasses.get(position);

        String title;
        double cart_price;

       if(holder.getAdapterPosition()>last_itemcount){
           recyc_anim = AnimationUtils.loadAnimation(context,R.anim.recyc_animation);
           holder.cons.startAnimation(recyc_anim);
           holder.img.setImageResource(coffeeModelClass.getImage());
           holder.tv.setText(coffeeModelClass.getTitle());
           holder.recycler_rupees.setText(String.valueOf(coffeeModelClass.getPrice()));
           holder.tv.getRootView();
//           last_itemcount = holder.getAdapterPosition();
       }

        title = coffeeModelClass.getTitle();
        cart_price = Double.parseDouble(String.valueOf(coffeeModelClass.getPrice()));

        holder.add.setOnClickListener(view -> Callbottomsheet(view, title, cart_price));
    }

    private void Callbottomsheet(View view1, String title, double cart_price) {

        card_animation_fade_in = AnimationUtils.loadAnimation(context,R.anim.fade);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.MyNewDialog);
        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = LayoutInflater.from(context).inflate(R.layout.cardview, view1.findViewById(R.id.relative_card));

        view.startAnimation(card_animation_fade_in);
        Button btn_add_to_cart, add;
        RadioButton radio_small, radio_reg, radio_large;
        ImageView sub_item, add_item;
        TextView item_tv;
        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);
        add = view.findViewById(R.id.add);
        sub_item = view.findViewById(R.id.item_sub);
        add_item = view.findViewById(R.id.item_add);
        item_tv = view.findViewById(R.id.item_tv);
        radio_small = view.findViewById(R.id.radio_small);
        radio_reg = view.findViewById(R.id.radio_regular);
        radio_large = view.findViewById(R.id.radio_large);
        radio_small.setChecked(true);
        radio_small.setTextColor(context.getResources().getColor(R.color.white));

        add.setVisibility(View.VISIBLE);

        add.setOnClickListener(view2 -> {
            add.setVisibility(View.GONE);
            sub_item.setVisibility(View.VISIBLE);
            add_item.setVisibility(View.VISIBLE);
            item_tv.setVisibility(View.VISIBLE);
            counter = 1;
            item_tv.setText(String.valueOf(counter));
            add_item.setOnClickListener(view3 -> {
                counter++;
                item_tv.setText(String.valueOf(counter));
            });
            sub_item.setOnClickListener(view3 -> {
                counter--;
                if (counter == 0) {
                    add.setVisibility(View.VISIBLE);
                    sub_item.setVisibility(View.GONE);
                    add_item.setVisibility(View.GONE);
                    item_tv.setVisibility(View.GONE);
                }
                item_tv.setText(String.valueOf(counter));
            });
        });
        radio_small.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_small.setTextColor(context.getResources().getColor(R.color.white));
                radio_reg.setChecked(false);
                radio_reg.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_large.setChecked(false);
                radio_large.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                size = radio_small.getText().toString();
            }
        });
        radio_reg.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_reg.setTextColor(context.getResources().getColor(R.color.white));
                radio_small.setChecked(false);
                radio_small.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_large.setChecked(false);
                radio_large.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                size = radio_reg.getText().toString();
            }
        });
        radio_large.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_large.setTextColor(context.getResources().getColor(R.color.white));
                radio_small.setChecked(false);
                radio_small.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_reg.setChecked(false);
                radio_reg.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                size = radio_large.getText().toString();
            }
        });
        btn_add_to_cart.setOnClickListener(view2 -> {
            if (counter == 0) {
                Toast.makeText(context, "Please Select Quantity", Toast.LENGTH_SHORT).show();
            } else {
                int cart_counter = Integer.parseInt(item_tv.getText().toString());
                databaseHelper = new DatabaseHelper(context);
                boolean check_inserted_data = databaseHelper.InsertData(title, cart_price, cart_counter, size);
                if (check_inserted_data) {
                    Toast.makeText(context, "Done!! Enjoy Your Coffee", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(context, "Something Went Wrong Please Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv, recycler_rupees;
        ConstraintLayout add,cons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.card_image);
            tv = itemView.findViewById(R.id.tv_title);
            cons = itemView.findViewById(R.id.cons);
            add = itemView.findViewById(R.id.cons1);
            recycler_rupees = itemView.findViewById(R.id.recycler_rupees);

        }
    }
}
