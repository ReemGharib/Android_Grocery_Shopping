package com.example.firstapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.firstapplication.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {
    CircleImageView uploaded_image;
    LinearLayout upload_image;
    TextView text_size_photo;
    Button signUp_btn;
    EditText email_txt, pass_txt, name_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent i = getIntent();
        if(i.getStringExtra("signUp").equals("first_sign_up")){
            Toast.makeText(getApplicationContext(), "first_signUp_frag", Toast.LENGTH_SHORT).show();
        }
        if(i.getStringExtra("signUp").equals("sec_sign_up")){
            Toast.makeText(getApplicationContext(), "sec_signUp_frag", Toast.LENGTH_SHORT).show();
        }
        if(i.getStringExtra("signUp").equals("third_sign_up")){
            Toast.makeText(getApplicationContext(), "third_signUp_frag", Toast.LENGTH_SHORT).show();
        }

        signUp_btn=(Button)findViewById(R.id.signUp_btn);
        email_txt=(EditText)findViewById(R.id.signUp_et_email);
        name_txt=(EditText)findViewById(R.id.signUp_et_name);
        pass_txt=(EditText)findViewById(R.id.signUp_et_pass);

        upload_image=(LinearLayout)findViewById(R.id.upload_image);
        uploaded_image=(CircleImageView)findViewById(R.id.uploaded_image);
        text_size_photo=(TextView)findViewById(R.id.text_size_photo);
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                uploaded_image.setImageURI(selectedImage);
                text_size_photo.setText("");
//                String filePath = getPath(selectedImage);
//                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);
//                Toast.makeText(getApplicationContext(), "file path"+filePath, Toast.LENGTH_SHORT).show();
//
//                if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
//                    //FINE
//                } else {
//                    //NOT IN REQUIRED FORMAT
//                }
            }

    }

}
