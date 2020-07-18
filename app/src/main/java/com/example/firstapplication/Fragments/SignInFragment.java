package com.example.firstapplication.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.firstapplication.Activities.FriutAndVegetableActivity;
import com.example.firstapplication.Entities.Order;
import com.example.firstapplication.Interfaces.OrderInterface;
import com.example.firstapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInFragment extends Fragment {
    TextView forgot_pass_txt;
    Button signIn_btn;
    OrderInterface orderInterface;
    String baseUrl="http://192.168.0.109:8080/";

    public SignInFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        signIn_btn = (Button)v.findViewById(R.id.signIn_btn);
        forgot_pass_txt = (TextView)v.findViewById(R.id.forgot_pass_txt);

        Gson gson =new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        forgot_pass_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.replace(R.id.frame_signAndForgotPass, new ForgotPasswordFragment());
                trans.commit();
            }
        });

        orderInterface=retrofit.create(OrderInterface.class);
//        signIn_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Order newOrder=new Order();
//                newOrder.setId("");
//                Call<Order> call=orderInterface.addOrder("c13d8c1d-6480-4cdd-89bc-5a36a5a44c9c", newOrder);
//                call.enqueue(new Callback<Order>() {
//                    @Override
//                    public void onResponse(Call<Order> call, Response<Order> response) {
//                        Intent i =new Intent(getContext(), FriutAndVegetableActivity.class);
//                        i.putExtra("sign_in_type", "shop nearby stores");
//                        i.putExtra("order_id", response.body().getId());
//                        startActivity(i);
//                    }
//                    @Override
//                    public void onFailure(Call<Order> call, Throwable t) {
//                    }
//                });
//
//            }
//        });

        signIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext(), FriutAndVegetableActivity.class);
                        i.putExtra("sign_in_type", "shop nearby stores");
                        startActivity(i);
            }
        });
        return v;
    }
}
