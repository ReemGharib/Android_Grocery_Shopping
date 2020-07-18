package com.example.firstapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.firstapplication.R;
import com.example.firstapplication.Activities.SignInAndForgotPassActivity;
import com.example.firstapplication.Activities.SignUpActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondMainFragment extends Fragment {
    Button signIn, signUp;
    public SecondMainFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_second_main, container, false);
        signIn =(Button)v.findViewById(R.id.sec_signIn_btn);
        signUp =(Button)v.findViewById(R.id.sec_signup_btn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), SignInAndForgotPassActivity.class);
                intent.putExtra("signIn","sec_sign_in");
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getContext(), SignUpActivity.class);
                i.putExtra("signUp","sec_sign_up");
                startActivity(i);
            }
        });
        return v;
    }
}
