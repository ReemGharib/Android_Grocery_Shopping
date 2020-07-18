package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapplication.Adapters.CheckoutAdapter;
import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.Interfaces.OrderInterface;
import com.example.firstapplication.Interfaces.OrderProductInterface;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity {

    ImageView checkout_back_img;
    Button checkout_btn;
    Intent intent;
    OrderInterface orderInterface;
    TextView checkout_total_price;
    RecyclerView recycler_checkout;
    OrderProductInterface orderProductInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        intent=getIntent();
        checkout_back_img=(ImageView)findViewById(R.id.checkout_back_img);
        checkout_btn=(Button)findViewById(R.id.checkout_btn);
        checkout_total_price=(TextView)findViewById(R.id.checkout_total_price);
        recycler_checkout=(RecyclerView)findViewById(R.id.recycler_checkout);


        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Success.class);
                startActivity(intent);
            }
        });

        orderInterface= MyApplication.connect().create(OrderInterface.class);
        String orderId=intent.getStringExtra("orderId");
        Call<Order> orderCall=orderInterface.addTotalPrice(orderId);
        orderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()){//delivery fee is $10
                    String totalPrice=Float.toString(response.body().getTotalPrice() + 10);
                    String totalPriceLL=Float.toString((response.body().getTotalPrice() +10) * 1500);
                    checkout_total_price.setText(totalPrice+ "$ /" + totalPriceLL + "L.L.");
                }
            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
        checkout_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager manager=new LinearLayoutManager(this);
        recycler_checkout.setLayoutManager(manager);
        recycler_checkout.setHasFixedSize(true);
        orderProductInterface=MyApplication.connect().create(OrderProductInterface.class);
        Call<ArrayList<OrderProduct>> call=orderProductInterface.getAllOrderProductToOrder(orderId);
        call.enqueue(new Callback<ArrayList<OrderProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<OrderProduct>> call, Response<ArrayList<OrderProduct>> response) {
                if (response.isSuccessful()){
                    CheckoutAdapter checkoutAdapter=new CheckoutAdapter(getApplicationContext(), response.body());
                    recycler_checkout.setAdapter(checkoutAdapter);
                    checkoutAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<OrderProduct>> call, Throwable t) {

            }
        });


    }
}
