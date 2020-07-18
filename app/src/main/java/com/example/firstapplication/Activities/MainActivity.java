package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.firstapplication.Adapters.DrawerLayoutAdapter;
import com.example.firstapplication.Adapters.MyPagerAdapter;
import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.Interfaces.InterfaceApi;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import java.io.Serializable;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;
    ImageView drawerLayout_img;
    DrawerLayout drawerLayout;
    RecyclerView recycler_drawerLayout;
    DrawerLayoutAdapter drawerLayoutAdapter;
    InterfaceApi interfaceApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIntent();
        drawerLayout_img=(ImageView)findViewById(R.id.drawerLayout_img);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        recycler_drawerLayout=(RecyclerView)findViewById(R.id.recycler_drawerLayout);

        drawerLayout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer((int) Gravity.LEFT);
            }
        });
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPage);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

        recycler_drawerLayout.setHasFixedSize(true);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recycler_drawerLayout.setLayoutManager(manager);

        interfaceApi= MyApplication.connect().create(InterfaceApi.class);
        Call<ArrayList<DrawerLayoutItem>> call=interfaceApi.getAllDrawer();
        call.enqueue(new Callback<ArrayList<DrawerLayoutItem>>() {
            @Override
            public void onResponse(Call<ArrayList<DrawerLayoutItem>> call, Response<ArrayList<DrawerLayoutItem>> response) {
                drawerLayoutAdapter=new DrawerLayoutAdapter(getApplicationContext(), response.body(), new DrawerLayoutAdapter.OnItemClickListenerDrawerLayout() {
                    @Override
                    public void onItemClick(DrawerLayoutItem item) {
                        Intent intent=new Intent(getApplicationContext(), NavigationItemActivity.class);
                        intent.putExtra("item", (Serializable) item);
                        startActivity(intent);
                    }
                });
                recycler_drawerLayout.setAdapter(drawerLayoutAdapter);
                drawerLayoutAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<DrawerLayoutItem>> call, Throwable t) {
            }
        });

    }
}
