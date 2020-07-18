package com.example.firstapplication.Interfaces;

import com.example.firstapplication.Entities.OrderProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderProductInterface {
    @GET("OrderProduct")
    Call<List<OrderProduct>> getAllOrderProduct();

    @GET("OrderProduct/getOrderProduct/{order_product_id}")
    Call<OrderProduct> getOrderProduct(@Path("order_product_id") String order_product_id);

    @POST("OrderProduct/addOrUpdate/{prod_id}/{order_id}")
    Call<OrderProduct> addOrUpdate(@Path("prod_id")String prod_id, @Path("order_id")String order_id);

    @GET("OrderProduct/getAllOrderProductToOrder/{order_id}")
    Call<ArrayList<OrderProduct>> getAllOrderProductToOrder(@Path("order_id")String order_id);
}
