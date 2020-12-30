package com.example.timetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class web_view extends AppCompatActivity {
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        webView = findViewById(R.id.webview);
        String text = intent.getStringExtra("hello");



        Log.d("ttt", text);


        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(text);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("MicroSoftTeam");
        getSupportActionBar().setSubtitle("RKU Platform");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_details:
                Intent intent = new Intent(getApplicationContext(), account_Details.class);
                startActivity(intent);
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Dev_info:
                Intent intent1 = new Intent(getApplicationContext(), Dev_info.class);
                startActivity(intent1);
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.sign_in:
                Intent intent2 = new Intent(getApplicationContext(), sign_in.class);
                startActivity(intent2);
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
