package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.firstapplication.Adapters.CategoriesDetailsAdapter;
import com.example.firstapplication.Entities.Category;
import com.example.firstapplication.Interfaces.InterfaceApi;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesDetailsActivity extends AppCompatActivity {
    CategoriesDetailsAdapter categoriesDetailsAdapter;
    RecyclerView recyclerView;
    InterfaceApi api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        getIntent();
        recyclerView=(RecyclerView)findViewById(R.id.shop_nearby_category_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        api= MyApplication.connect().create(InterfaceApi.class);
        Call<ArrayList<Category>> call =api.getAllCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if(response.isSuccessful()){
                    categoriesDetailsAdapter=new CategoriesDetailsAdapter(getApplicationContext(), response.body());
                    recyclerView.setAdapter(categoriesDetailsAdapter);
                    categoriesDetailsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

            }
        });
    }
}
