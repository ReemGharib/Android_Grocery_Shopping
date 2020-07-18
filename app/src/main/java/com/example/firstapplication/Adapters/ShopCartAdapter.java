package com.example.firstapplication.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ShopCartViewHolder>{
    Context context;
    OnItemClickListenerShopCart listenerShopCart;
    ArrayList<OrderProduct> orderProductArrayList;
    public ShopCartAdapter(Context c, ArrayList<OrderProduct> arrayList, OnItemClickListenerShopCart listenerShopCart){
        context=c;
        orderProductArrayList=arrayList;
        this.listenerShopCart=listenerShopCart;
    }
    @Override
    public ShopCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cart_item, parent, false);
         return new ShopCartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ShopCartViewHolder holder, final int position) {
        holder.category_name_cart.setText(orderProductArrayList.get(position).getProduct().getCategory().getCategoryName());
        holder.prod_name_cart.setText(orderProductArrayList.get(position).getProduct().getName());
        holder.prod_quantity_cart.setText("Quantity: " + orderProductArrayList.get(position).getQuantity().toString());
        MyApplication.orderProductArrayList.get(position).setQuantity(Integer.parseInt(orderProductArrayList.get(position).getQuantity().toString()));
        String txt=Float.toString( orderProductArrayList.get(position).getProduct().getPrice());
        String totalPrice=Float.toString( orderProductArrayList.get(position).getTotalPrice());
        holder.product_price_cart.setText("Unit price: $"+txt +"\nTotal price: $"+totalPrice);
        holder.bind(orderProductArrayList.get(position), listenerShopCart);
        Glide.with(context)
                .load(orderProductArrayList.get(position).getProduct().getImageUrl())
                .into(holder.prod_cart_img);

        holder.minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(holder.counter_quantity.getText().toString());
                if(i>0){
                    i--;
                    holder.counter_quantity.setText(Integer.toString(i));
                    holder.prod_quantity_cart.setText("Quantity: "+i);
                    MyApplication.orderProductArrayList.get(position).setQuantity(i);
                }
            }
        });
        holder.plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(holder.counter_quantity.getText().toString());
                if(i < orderProductArrayList.get(position).getProduct().getQuantityInStock()){
                    i++;
                    holder.counter_quantity.setText(Integer.toString(i));
                    holder.prod_quantity_cart.setText("Quantity: "+i);
                    MyApplication.orderProductArrayList.get(position).setQuantity(i);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return orderProductArrayList.size();
    }

    public interface OnItemClickListenerShopCart{
        void onItemClick(OrderProduct item);
    }

    static class ShopCartViewHolder extends RecyclerView.ViewHolder{
        ImageView prod_cart_img;
        TextView category_name_cart, prod_name_cart, prod_quantity_cart, product_price_cart, counter_quantity;
        ImageView minus_img, plus_img;
        public ShopCartViewHolder(View itemView) {
            super(itemView);
            prod_cart_img=itemView.findViewById(R.id.shop_cart_img);
            category_name_cart=itemView.findViewById(R.id.category_name_cart);
            prod_name_cart=itemView.findViewById(R.id.prod_name_cart);
            prod_quantity_cart=itemView.findViewById(R.id.prod_quantity_cart);
            product_price_cart=itemView.findViewById(R.id.product_price_cart);

            minus_img=itemView.findViewById(R.id.minus_img);
            plus_img=itemView.findViewById(R.id.plus_img);
            counter_quantity=itemView.findViewById(R.id.counter_quantity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        public void bind(final OrderProduct item, final ShopCartAdapter.OnItemClickListenerShopCart listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
