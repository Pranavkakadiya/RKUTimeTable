package com.example.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Dev_info extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer Info");
        getSupportActionBar().setSubtitle("TimeTable App");

        textView=findViewById(R.id.rkweb);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), web_view.class);
                intent.putExtra("hello","https://www.rku.ac.in/");
                startActivity(intent);
            }
        });


    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
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