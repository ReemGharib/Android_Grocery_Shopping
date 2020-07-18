package com.example.firstapplication.Fragments.NavigationFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.R;

import java.io.Serializable;

public class NotificationFragment extends Fragment {
    Bundle bundle;
    TextView name;
    public NotificationFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_notifictaion, container, false);
        name=(TextView)v.findViewById(R.id.notification_name);
        bundle=getArguments();
        Serializable item=bundle.getSerializable("item");
        DrawerLayoutItem drawerLayoutItem=(DrawerLayoutItem)item;
        name.setText(drawerLayoutItem.getName());

        return v;
    }
}
