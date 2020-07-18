package com.example.firstapplication.Interfaces;

import com.example.firstapplication.Entities.Category;
import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.Entities.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceApi {
    //Categories
    @GET("Categories")
    Call<ArrayList<Category>> getAllCategories();

    @POST("Categories/addOrUpdate")
    Call<Category> addOrUpdate(@Body Category category);

    @DELETE("Categories/{id}")
    Call delete(@Path("id") String id);

    @GET("Categories/getAllProductsToCategory/{category_id}")
    Call<ArrayList<Product>> getAllProductToCategory(@Path("category_id")String category_id);

    //Products
    @GET("Products")
    Call<ArrayList<Product>> getAllProducts();

    @POST("Products/addOrUpdate/{category_id}")
    Call<Product> addOrUpdate(@Path("category_id")String category_id);

    //DrawerLayout
    @GET("DrawerLayout")
    Call<ArrayList<DrawerLayoutItem>> getAllDrawer();



}
