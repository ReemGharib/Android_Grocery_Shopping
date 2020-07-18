
package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.firstapplication.Fragments.ForgotPasswordFragment;
import com.example.firstapplication.Fragments.SignInFragment;
import com.example.firstapplication.R;

public class SignInAndForgotPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_and_forgot_pass);

        Intent i =getIntent();
        if(i.getStringExtra("signIn").equals("first_sign_in") || i.getStringExtra("signIn").equals("sec_sign_in") || i.getStringExtra("signIn").equals("third_sign_in")){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.replace(R.id.frame_signAndForgotPass, new SignInFragment());
            trans.commit();
        }
        else{
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_signAndForgotPass, new ForgotPasswordFragment());
            transaction.commit();
        }

    }
}
