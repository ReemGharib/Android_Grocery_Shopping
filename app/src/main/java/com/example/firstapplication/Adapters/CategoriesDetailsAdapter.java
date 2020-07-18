package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firstapplication.Entities.Category;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class CategoriesDetailsAdapter extends RecyclerView.Adapter<CategoriesDetailsAdapter.ViewHolderShopNearbyCategory> {
    ArrayList<Category> categoryEntities;
    Context _context;
    public CategoriesDetailsAdapter(Context c, ArrayList<Category> array){
        categoryEntities=array;
        _context=c;
    }
    @Override
    public ViewHolderShopNearbyCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_details_item, parent, false);
        return new ViewHolderShopNearbyCategory(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderShopNearbyCategory holder, int position) {
        holder.categoryName.setText(categoryEntities.get(position).getCategoryName());
        holder.nbOfProducts.setText(categoryEntities.get(position).getNbOfProducts().toString() + " items");
        Glide.with(_context)
                .load(categoryEntities.get(position).getImageUrl())
                .into(holder.category_image);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return categoryEntities.size();
    }

    static class ViewHolderShopNearbyCategory extends RecyclerView.ViewHolder{
        TextView categoryName;
        TextView nbOfProducts;
        ImageView category_image;
        public ViewHolderShopNearbyCategory(@NonNull View itemView) {
            super(itemView);
            nbOfProducts=(TextView)itemView.findViewById(R.id.nb_of_products);
            categoryName=(TextView)itemView.findViewById(R.id.category_name);
            category_image=(ImageView)itemView.findViewById(R.id.category_details_image);
        }
    }
}
