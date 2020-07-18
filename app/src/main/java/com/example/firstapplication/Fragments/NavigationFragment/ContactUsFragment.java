package com.example.firstapplication.Fragments.NavigationFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapplication.Entities.DrawerLayoutItem;
import com.example.firstapplication.R;
import java.io.Serializable;

public class ContactUsFragment extends Fragment {
    Bundle bundle;
    TextView name;
    TextView contact_phone, contact_facebook, contact_website, contact_email;
    WebView webView;
    ImageView contactUs_back;
    public ContactUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_contact_us, container, false);
        contactUs_back=(ImageView)v.findViewById(R.id.contactUs_back);
        name=(TextView)v.findViewById(R.id.contactUs_name);
        contact_phone=(TextView)v.findViewById(R.id.contact_phone);
        contact_facebook=(TextView)v.findViewById(R.id.contact_facebook);
        contact_website=(TextView)v.findViewById(R.id.contact_website);
        contact_email=(TextView)v.findViewById(R.id.contact_email);
        webView=(WebView)v.findViewById(R.id.webView_contact);

        bundle=getArguments();
        Serializable item=bundle.getSerializable("item");
        DrawerLayoutItem drawerLayoutItem=(DrawerLayoutItem)item;
        name.setText(drawerLayoutItem.getName());

        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.google.com");

        contactUs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        contact_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contact_phone.getText().toString(), null));
                startActivity(intent);
            }
        });
        contact_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(contact_facebook.getText().toString()));
                startActivity(intent);
            }
        });
        contact_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://" +contact_website.getText().toString()));
                startActivity(intent);
            }
        });
        contact_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{contact_email.getText().toString()});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });


        return v;
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
