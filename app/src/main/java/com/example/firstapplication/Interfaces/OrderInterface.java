package com.example.firstapplication.Interfaces;

import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Entities.OrderProduct;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderInterface {
    @POST("Orders/addOrUpdate/{customer_id}")
    Call<Order> addOrder(@Path("customer_id") String customer_id, @Body Order order);

    @POST("OrderProduct/addOrUpdate/{prod_id}/{order_id}")
    Call<OrderProduct> addOrderProduct(@Body OrderProduct orderProduct, @Path("prod_id") String prod_id, @Path("order_id")String order_id);

    @POST("Orders/addTotalPrice/{order_id}")
    Call<Order> addTotalPrice(@Path("order_id")String order_id);

    @GET("Orders/getAllOrderToCustomer/{customer_id}")
    Call<ArrayList<Order>> getAllOrderToCustomer(@Path("customer_id")String customer_id);
}
