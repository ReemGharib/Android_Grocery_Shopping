package com.example.firstapplication.Fragments.NavigationFragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.R;

import java.io.Serializable;

public class AboutUsFragment extends Fragment {
    Bundle bundle;
    TextView aboutUs_name;
    ImageView aboutUs_back;
    public AboutUsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_about_us, container, false);
        aboutUs_name=(TextView)v.findViewById(R.id.aboutUs_name);
        aboutUs_back=(ImageView)v.findViewById(R.id.aboutUs_back);
        bundle=getArguments();
        Serializable item=bundle.getSerializable("item");
        DrawerLayoutItem drawerLayoutItem=(DrawerLayoutItem)item;
        aboutUs_name.setText(drawerLayoutItem.getName());
        aboutUs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }
}
