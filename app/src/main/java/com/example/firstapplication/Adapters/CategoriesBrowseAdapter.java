package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.firstapplication.Entities.Category;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class CategoriesBrowseAdapter extends RecyclerView.Adapter<CategoriesBrowseAdapter.ViewHolderFoodAdapter> {
    private ArrayList<Category> categories;
    private Context context;
    OnItemClickListenerCategory listenerCategory;
    int row_index =0;
    public CategoriesBrowseAdapter(Context c, ArrayList<Category> list, OnItemClickListenerCategory listener){
      context=c;
      categories=list;
      listenerCategory=listener;
    }
    @Override
    public ViewHolderFoodAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolderFoodAdapter(context, v);
    }

    @Override
    public void onBindViewHolder(final ViewHolderFoodAdapter holder, final int position) {
        Toast.makeText(context, "h "+categories.get(position),Toast.LENGTH_LONG).show();
        holder.food_title.setText(categories.get(position).getCategoryName());
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.first_slide));
        holder.bind(categories.get(position), listenerCategory);
        Glide.with(context)
                .load(categories.get(position).getImageUrl())
                .into(holder.imageView);

//       ` holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                row_index=position;
//                notifyDataSetChanged();
//            }
//        });
//        if(row_index == position){
//            holder.linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.green_circle));
//        }
//        else{
//            holder.linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_gray));
//        }`
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnItemClickListenerCategory{
        void onItemClick(Category item);
    }
    static class ViewHolderFoodAdapter extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public TextView food_title;
        public ImageView imageView;

        public ViewHolderFoodAdapter(final Context c, View itemView){
            super(itemView);
            food_title=(TextView)itemView.findViewById(R.id.food_title);
            imageView=(ImageView)itemView.findViewById(R.id.food_img);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linear_food_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        public void bind(final Category item, final CategoriesBrowseAdapter.OnItemClickListenerCategory listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
