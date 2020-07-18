package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstapplication.Adapters.ShopCartAdapter;
import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.Interfaces.OrderInterface;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartShopActivity extends AppCompatActivity {
    RecyclerView recycler_shop_cart;
    OrderInterface shopCartInterface;
    ShopCartAdapter shopCartAdapter;
    OrderInterface orderInterface;
    TextView total_price_cart_act;
    String order_id;
    Button checkout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shop);


        final Intent intent=getIntent();
        total_price_cart_act=(TextView)findViewById(R.id.total_price_cart_act);
        checkout_btn=(Button)findViewById(R.id.checkout_btn);

        order_id = intent.getStringExtra("order_id");
        recycler_shop_cart=(RecyclerView)findViewById(R.id.recycler_shop_cart);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recycler_shop_cart.setLayoutManager(manager);
        recycler_shop_cart.setHasFixedSize(true);

        shopCartAdapter=new ShopCartAdapter(getApplicationContext(), MyApplication.orderProductArrayList, new ShopCartAdapter.OnItemClickListenerShopCart() {
            @Override
            public void onItemClick(OrderProduct item) {

            }
        });
        recycler_shop_cart.setAdapter(shopCartAdapter);
        shopCartAdapter.notifyDataSetChanged();

//        shopCartInterface= MyApplication.connect().create(OrderProductInterface.class);
//        Call<ArrayList<OrderProduct>> call =shopCartInterface.getAllOrderProductToOrder(order_id);
//        call.enqueue(new Callback<ArrayList<OrderProduct>>() {
//            @Override
//            public void onResponse(Call<ArrayList<OrderProduct>> call, Response<ArrayList<OrderProduct>> response) {
//                if(response.isSuccessful()){
//                    shopCartAdapter=new ShopCartAdapter(getApplicationContext(), response.body(), new ShopCartAdapter.OnItemClickListenerShopCart() {
//                        @Override
//                        public void onItemClick(OrderProduct item) {
//
//                        }
//                    });
//                    recycler_shop_cart.setAdapter(shopCartAdapter);
//                    shopCartAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<OrderProduct>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "failed to connect", Toast.LENGTH_SHORT).show();
//            }
//        });

        orderInterface=MyApplication.connect().create(OrderInterface.class);
        Order newOrder=new Order();
        newOrder.setId("");
        Call<Order> call=orderInterface.addOrder("c13d8c1d-6480-4cdd-89bc-5a36a5a44c9c", newOrder);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                order_id=response.body().getId();
            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
            }
        });

//        Call<Order> orderCall=orderInterface.addTotalPrice(order_id);
//        orderCall.enqueue(new Callback<Order>() {
//            @Override
//            public void onResponse(Call<Order> call, Response<Order> response) {
//                if(response.isSuccessful()){//delivery fee is $10
//                    String totalPrice=Float.toString(response.body().getTotalPrice() + 10);
//                    String totalPriceLL=Float.toString((response.body().getTotalPrice() +10) * 1500);
//                    total_price_cart_act.setText(totalPrice+ "$ /" + totalPriceLL + "L.L.");
//                }
//            }
//            @Override
//            public void onFailure(Call<Order> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
//            }
//        });

        shopCartInterface=MyApplication.connect().create(OrderInterface.class);
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<MyApplication.orderProductArrayList.size();i++){
                    Call<OrderProduct> call1=shopCartInterface.addOrderProduct(MyApplication.orderProductArrayList.get(i), MyApplication.orderProductArrayList.get(i).getProduct().getId(), order_id);
                    call1.enqueue(new Callback<OrderProduct>() {
                        @Override
                        public void onResponse(Call<OrderProduct> call, Response<OrderProduct> response) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            response.body();
                        }
                        @Override
                        public void onFailure(Call<OrderProduct> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                Intent intent1=new Intent(getApplicationContext(), Checkout.class);
                intent1.putExtra("orderId", order_id);
                startActivity(intent1);
            }
        });
    }
}
