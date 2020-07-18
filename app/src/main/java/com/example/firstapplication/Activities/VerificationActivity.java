package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstapplication.R;

public class VerificationActivity extends AppCompatActivity {
    Button verifying_code_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verifying_code_btn=(Button)findViewById(R.id.verifying_code_btn);
        verifying_code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
