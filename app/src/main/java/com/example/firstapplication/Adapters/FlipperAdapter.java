package com.example.firstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.firstapplication.R;

public class FlipperAdapter extends BaseAdapter {
    int [] images;
    Context context;
    LayoutInflater inflater;

    public FlipperAdapter(Context c, int[] images){
        this.images=images;
        context=c;
        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.list_flipper_item, null);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.image_flipper);
        imageView.setImageResource(images[position]);
        return convertView;
    }
}
