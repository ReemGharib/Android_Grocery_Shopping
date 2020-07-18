package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.Fragments.NavigationFragment.AboutUsFragment;
import com.example.firstapplication.Fragments.NavigationFragment.ContactUsFragment;
import com.example.firstapplication.Fragments.NavigationFragment.NotificationFragment;
import com.example.firstapplication.Fragments.NavigationFragment.OrderFragment;
import com.example.firstapplication.R;

import java.io.Serializable;

public class NavigationItemActivity extends AppCompatActivity {
    DrawerLayoutItem drawerLayoutItem;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_item);

        Intent intent=getIntent();
        Serializable item= intent.getSerializableExtra("item");
        drawerLayoutItem= (DrawerLayoutItem) item;
        manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        if(drawerLayoutItem.getName().equals("Contact Us")){
            Bundle bundle=new Bundle();
            bundle.putSerializable("item", drawerLayoutItem);
            ContactUsFragment contactUsFragment = new ContactUsFragment();
            contactUsFragment.setArguments(bundle);
            transaction.replace(R.id.frame_navigation, contactUsFragment);
            transaction.commit();
        }
        if(drawerLayoutItem.getName().equals("Notifications")){
            Bundle bundle=new Bundle();
            bundle.putSerializable("item", drawerLayoutItem);
            NotificationFragment fragment = new NotificationFragment();
            fragment.setArguments(bundle);
            transaction.replace(R.id.frame_navigation,fragment);
            transaction.commit();
        }
        if(drawerLayoutItem.getName().equals("About Us")){
            Bundle bundle=new Bundle();
            bundle.putSerializable("item", drawerLayoutItem);
            AboutUsFragment aboutUsFragment =new AboutUsFragment();
            aboutUsFragment.setArguments(bundle);
            transaction.replace(R.id.frame_navigation, aboutUsFragment);
            transaction.commit();
        }
        if(drawerLayoutItem.getName().equals("Orders")){
            Bundle bundle=new Bundle();
            bundle.putSerializable("item", drawerLayoutItem);
            OrderFragment orderFragment =new OrderFragment();
            orderFragment.setArguments(bundle);
            transaction.replace(R.id.frame_navigation, orderFragment);
            transaction.commit();
        }
        if(drawerLayoutItem.getName().equals("Categories")){
           Intent intent1=new Intent(getApplicationContext(), CategoriesDetailsActivity.class);
           startActivity(intent1);
        }
    }
}
