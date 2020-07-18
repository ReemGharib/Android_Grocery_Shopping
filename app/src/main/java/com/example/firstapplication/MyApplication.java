package com.example.firstapplication;

import android.app.Application;

import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.Entities.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static String baseUrl="http://192.168.0.109:8080/";
    public static ArrayList<Product> productArrayList=new ArrayList<Product>();
    public static ArrayList<OrderProduct> orderProductArrayList=new ArrayList<OrderProduct>();

    public static ArrayList<OrderProduct> test=new ArrayList<OrderProduct>();

    public static Retrofit connect(){
        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MyApplication.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
