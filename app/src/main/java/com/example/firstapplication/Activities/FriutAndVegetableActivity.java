package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapplication.Adapters.FlipperAdapter;
import com.example.firstapplication.Adapters.ProductsAdapter;
import com.example.firstapplication.Entities.Category;
import com.example.firstapplication.Entities.Product;
import com.example.firstapplication.Interfaces.InterfaceApi;
import com.example.firstapplication.Adapters.CategoriesBrowseAdapter;
import com.example.firstapplication.Adapters.MainSlideImageAdapter;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriutAndVegetableActivity extends AppCompatActivity {
    ViewPager viewPager_fruit_veg;
    MainSlideImageAdapter mainSlideImageAdapter;
    AdapterViewFlipper flipper;
    RecyclerView category_recycler;
    RecyclerView recycler_products;
    CategoriesBrowseAdapter categories_adapter;
    ArrayList<Product> listProductToCategory;
    InterfaceApi api;
    int c=0;
    LinearLayout linearLayout;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friut_and_vegetable);

        intent=getIntent();

        linearLayout=(LinearLayout)findViewById(R.id.linear_food_item);
       // viewPager_fruit_veg=(ViewPager)findViewById(R.id.viewPager_fruit_veg);
        category_recycler=(RecyclerView)findViewById(R.id.recycler_fruit_veg);
        recycler_products=(RecyclerView)findViewById(R.id.recycler_fg);

//        mainSlideImageAdapter=new MainSlideImageAdapter(getApplicationContext());
//        viewPager_fruit_veg.setAdapter(mainSlideImageAdapter);

        flipper=(AdapterViewFlipper)findViewById(R.id.flipper);
        int[] images={
                R.drawable.first_slide,
                R.drawable.second_slide,
                R.drawable.third_slide
        };
        FlipperAdapter flipperAdapter=new FlipperAdapter(getApplicationContext(), images);
        flipper.setAdapter(flipperAdapter);
        flipper.setFlipInterval(2600);
        flipper.setAutoStart(true);

        LinearLayoutManager manager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        category_recycler.setLayoutManager(manager);
        category_recycler.setHasFixedSize(true);
        category_recycler.setHorizontalScrollBarEnabled(true);

        api=MyApplication.connect().create(InterfaceApi.class);
        Call<ArrayList<Category>> call =api.getAllCategories();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    categories_adapter=new CategoriesBrowseAdapter(getApplicationContext(), response.body(), new CategoriesBrowseAdapter.OnItemClickListenerCategory() {
                        @Override
                        public void onItemClick(Category item) {
                            displayProductsToCategory(item);
                        }
                    });
                    if(c==0){//to display products for first category because at first time no one is clicked
                        displayProductsToCategory(response.body().get(0));
                    }
                    category_recycler.setAdapter(categories_adapter);
                    categories_adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });

        if(intent.getStringExtra("sign_in_type").equals("shop nearby stores")){
            Toast.makeText(getApplicationContext(), "shop nearby stores",Toast.LENGTH_SHORT).show();
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recycler_products.setLayoutManager(mLayoutManager);
        recycler_products.setHasFixedSize(true);

    }
    public void displayProductsToCategory(Category item){
        Toast.makeText(getApplicationContext(), "hh", Toast.LENGTH_SHORT).show();
        Call<ArrayList<Product>> callProductCategory=api.getAllProductToCategory(item.getId());

        callProductCategory.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if(response.isSuccessful()){
                    listProductToCategory=response.body();
                    ProductsAdapter adapterRecyclerFrVg=new ProductsAdapter(getApplicationContext(), listProductToCategory, new ProductsAdapter.OnItemClickListenerProduct() {
                        @Override
                        public void onItemClick(Product item) {
                            Intent i = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                             i.putExtra("order_id", intent.getStringExtra("order_id"));
                              i.putExtra("product",item);
                              startActivity(i);
                        }
                    });
                    recycler_products.setAdapter(adapterRecyclerFrVg);
                    adapterRecyclerFrVg.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
            }
        });
    }
}
