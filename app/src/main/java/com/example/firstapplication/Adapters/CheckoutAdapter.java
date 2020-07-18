package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutHolder> {
    Context context;
    ArrayList<OrderProduct> orderProductArrayList;
    public CheckoutAdapter(Context c, ArrayList<OrderProduct> orderProducts){
        context=c;
        orderProductArrayList=orderProducts;
    }

    @NonNull
    @Override
    public CheckoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_checkout_item, parent, false);
        return new CheckoutHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutHolder holder, int position) {
        String prod_name, product_total_quantity;
        prod_name=orderProductArrayList.get(position).getProduct().getName();
        product_total_quantity=orderProductArrayList.get(position).getQuantity().toString();
        holder.checkout_product_name.setText(prod_name +" x "+product_total_quantity);
        String t=Float.toString(orderProductArrayList.get(position).getTotalPrice());
        holder.checkout_product_total_price.setText("$"+t);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }

    static public class CheckoutHolder extends RecyclerView.ViewHolder{
        TextView checkout_product_name, checkout_product_total_price;
        public CheckoutHolder(@NonNull View itemView) {
            super(itemView);
            checkout_product_name=itemView.findViewById(R.id.checkout_product_name);
            checkout_product_total_price=itemView.findViewById(R.id.checkout_product_total_price);
        }
    }
}
