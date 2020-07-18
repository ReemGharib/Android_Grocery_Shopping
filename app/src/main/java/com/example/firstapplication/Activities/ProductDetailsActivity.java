package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firstapplication.Entities.OrderProduct;
import com.example.firstapplication.Entities.Product;
import com.example.firstapplication.Interfaces.OrderInterface;
import com.example.firstapplication.MyApplication;
import com.example.firstapplication.R;
import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView heart_image, imageProduct;
    Boolean check =true;
    LinearLayout bg_love;
    String order_id;
    TextView old_price, new_price, desc_txt, product_txt, quantityInStock, totalQuantity, categoryName;
    Spinner spinner_gram;
    String product_id;
    OrderProduct orderProduct;
    Button addToCart, viewCart;
    Product product;
    OrderInterface orderInterface;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailss);

        final Intent intent=getIntent();

        viewCart=(Button)findViewById(R.id.viewCart);
        addToCart=(Button)findViewById(R.id.add_to_cart_btn);

        heart_image = (ImageView) findViewById(R.id.toggle_btn_heart);
        bg_love = (LinearLayout)findViewById(R.id.bg_love);
        categoryName=(TextView)findViewById(R.id.category_name);
        old_price= (TextView)findViewById(R.id.old_price);
        new_price= (TextView)findViewById(R.id.new_price);
        desc_txt= (TextView)findViewById(R.id.desc_txt);
        product_txt= (TextView)findViewById(R.id.product_txt);
       // spinner_gram = (Spinner)findViewById(R.id.gram_spinner);
        quantityInStock=(TextView)findViewById(R.id.quantityInStock);
        totalQuantity=(TextView)findViewById(R.id.totalQuantity);
        imageProduct=(ImageView)findViewById(R.id.imageProduct);


        product=(Product)intent.getSerializableExtra("product");
        bg_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.equals(true)){
                    heart_image.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_heart));
                    check=false;
                }else{
                    heart_image.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_heart_button));
                    check=true;
                }
            }
        });

        old_price.setPaintFlags(old_price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);//middle line
        categoryName.setText(product.getCategory().getCategoryName());
        product_txt.setText(product.getName());
        old_price.setText(product.getOldPrice());
        String newPrice=Float.toString(product.getPrice());
        new_price.setText("$ "+newPrice);
        desc_txt.setText(product.getDescription());
        Glide.with(getApplicationContext())
                .load(product.getImageUrl())
                .into(imageProduct);
        String quantity_in_stock=Integer.toString(product.getQuantityInStock());
        String total_quantity=Integer.toString(product.getTotalQuantity());
        quantityInStock.setText(quantity_in_stock);
        totalQuantity.setText(total_quantity);

//        List<String> list = new ArrayList<String>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_gram.setAdapter(dataAdapter);

        orderInterface=MyApplication.connect().create(OrderInterface.class);
//        orderProduct =new OrderProduct();
//        orderProduct.setQuantity(3);
//        orderProduct.setTotalPrice(2000);
//        orderProduct.setId("");
//        product_id=product.getId();
//        order_id=intent.getStringExtra("order_id");

//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<OrderProduct> call_order_prod=orderInterface.addOrderProduct(orderProduct, product_id, order_id);
//                call_order_prod.enqueue(new Callback<OrderProduct>() {
//                    @Override
//                    public void onResponse(Call<OrderProduct> call, Response<OrderProduct> response) {
//                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                        response.body();
//                    }
//
//                    @Override
//                    public void onFailure(Call<OrderProduct> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderProduct orderProduct=new OrderProduct();
                orderProduct.setId("");
                orderProduct.setQuantity(1);
                orderProduct.setTotalPrice(2000);
                orderProduct.setProduct(product);
                MyApplication.orderProductArrayList.add(orderProduct);
                ArrayList<OrderProduct> o =MyApplication.orderProductArrayList;
            }
        });

        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t =new Intent(getApplicationContext(), CartShopActivity.class);
                t.putExtra("order_id", order_id);
                MyApplication.productArrayList=productArrayList;
                startActivity(t);
            }
        });
    }
}
