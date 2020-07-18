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
import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.R;

import java.util.ArrayList;

public class DrawerLayoutAdapter extends  RecyclerView.Adapter<DrawerLayoutAdapter.DrawerLayoutViewHolder>{
    Context _context;
    ArrayList<DrawerLayoutItem> arrayList;
    OnItemClickListenerDrawerLayout listenerDrawerLayout;

    public DrawerLayoutAdapter(Context c, ArrayList<DrawerLayoutItem> list, OnItemClickListenerDrawerLayout listener){
        _context=c;
        arrayList=list;
        listenerDrawerLayout=listener;
    }
    @Override
    public DrawerLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerlayout_item, parent, false);
        return new DrawerLayoutViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerLayoutViewHolder holder, int position) {
        holder.drawer_text.setText(arrayList.get(position).getName());
        Glide.with(_context).load(arrayList.get(position).getImageUrl()).into(holder.drawer_img_item);
        holder.bind(arrayList.get(position), listenerDrawerLayout);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface OnItemClickListenerDrawerLayout{
        void onItemClick(DrawerLayoutItem item);
    }

    static class DrawerLayoutViewHolder extends RecyclerView.ViewHolder{
        TextView drawer_text;
        ImageView drawer_img_item;
        public DrawerLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            drawer_img_item=itemView.findViewById(R.id.drawerLayout_img_item);
            drawer_text=itemView.findViewById(R.id.drawerLayout_txt_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        public void bind(final DrawerLayoutItem item, final DrawerLayoutAdapter.OnItemClickListenerDrawerLayout listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
