package com.rahi.coffee_shope.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.rahi.coffee_shope.POJO.CartModelClass;
import com.rahi.coffee_shope.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<CartModelClass> cartmodel;
    Context context;
    public int counter = 0;

    public CartAdapter(ArrayList<CartModelClass> cartmodel, Context context) {
        this.cartmodel = cartmodel;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartmodellayout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartModelClass cartModelClass = cartmodel.get(position);

        holder.cart_item_count.setText("x"+cartModelClass.getCounter()+" "+cartModelClass.getSize());
        holder.cart_item_price.setText(String.valueOf(cartModelClass.getPrice()));
        holder.cart_item_title.setText(cartModelClass.getTitle());
        holder.cart_cons_layout.setOnLongClickListener(view -> {
           showeditdialogsheet(view);
            return true;
        });
    }

    private void showeditdialogsheet(View view1) {

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.MyNewDialog);
        bottomSheetDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = LayoutInflater.from(context).inflate(R.layout.cardview, view1.findViewById(R.id.relative_card));
        Button btn_add_to_cart, add;
        RadioButton radio_small, radio_reg, radio_large;
        ImageView sub_item, add_item;
        TextView item_tv;

        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);
        add = view.findViewById(R.id.add);
        sub_item = view.findViewById(R.id.item_sub);
        add_item = view.findViewById(R.id.item_add);
        item_tv = view.findViewById(R.id.item_tv);

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
//            item_tv.setBackgroundColor(context.getResources().getColor(R.color.white));
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

        radio_small = view.findViewById(R.id.radio_small);
        radio_reg = view.findViewById(R.id.radio_regular);
        radio_large = view.findViewById(R.id.radio_large);
        radio_small.setChecked(true);
        radio_small.setTextColor(context.getResources().getColor(R.color.white));

        radio_small.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_small.setTextColor(context.getResources().getColor(R.color.white));
                radio_reg.setChecked(false);
                radio_reg.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_large.setChecked(false);
                radio_large.setTextColor(context.getResources().getColor(R.color.md_orange_500));
            }
        });
        radio_reg.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_reg.setTextColor(context.getResources().getColor(R.color.white));
                radio_small.setChecked(false);
                radio_small.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_large.setChecked(false);
                radio_large.setTextColor(context.getResources().getColor(R.color.md_orange_500));
            }
        });
        radio_large.setOnCheckedChangeListener((compoundButton, ischecked) -> {
            if (ischecked) {
                radio_large.setTextColor(context.getResources().getColor(R.color.white));
                radio_small.setChecked(false);
                radio_small.setTextColor(context.getResources().getColor(R.color.md_orange_500));
                radio_reg.setChecked(false);
                radio_reg.setTextColor(context.getResources().getColor(R.color.md_orange_500));
            }
        });
        btn_add_to_cart.setOnClickListener(view2 -> {
//            boolean check_inserted_data = databaseHelper.InsertData(title, cart_price, cart_counter);
//            if (check_inserted_data) {
//                Toast.makeText(context, "Done!! Enjoy Your Coffee", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(context, "Something Went Wrong Please Try Again Later", Toast.LENGTH_SHORT).show();
//            }
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    @Override
    public int getItemCount() {
        return cartmodel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cart_item_price,cart_item_title,cart_item_count;
        ConstraintLayout cart_cons_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_item_count = itemView.findViewById(R.id.cart_item_count);
            cart_item_title = itemView.findViewById(R.id.cart_item_title);
            cart_item_price = itemView.findViewById(R.id.cart_item_price);
            cart_cons_layout = itemView.findViewById(R.id.cart_cons_layout);
        }
    }
}
