package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstapplication.Entities.Product;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolderFrVg> {
    OnItemClickListenerProduct listenerProduct;
    ArrayList<Product> productList;
    Context context;
    public ProductsAdapter(Context c, ArrayList<Product> array, OnItemClickListenerProduct listener){
        listenerProduct = listener;
        productList = array;
        context=c;
    }
    @Override
    public ViewHolderFrVg onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolderFrVg(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderFrVg holder, int position) {
        holder.product_txt.setText(productList.get(position).getName());
        String txt=Float.toString(productList.get(position).getPrice());
        holder.price_txt.setText("$ "+txt);
        Glide.with(context)
                .load(productList.get(position).getImageUrl())
                .into(holder.imageView);

        holder.bind(productList.get(position), listenerProduct);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public interface OnItemClickListenerProduct{
        void onItemClick(Product item);

    }

    static class ViewHolderFrVg extends RecyclerView.ViewHolder{
        TextView price_txt, product_txt;
        ImageView imageView;
        public ViewHolderFrVg(View item){
            super(item);
            price_txt=item.findViewById(R.id.price_txt);
            product_txt=item.findViewById(R.id.product_txt);
            imageView=item.findViewById(R.id.product_img);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        public void bind(final Product item, final OnItemClickListenerProduct listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
