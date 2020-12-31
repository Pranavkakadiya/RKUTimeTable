package com.example.timetable;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {//implements NavigationView.OnNavigationItemSelectedListener
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private RequestQueue mRequestQueue;
    //DrawerLayout drawerLayout;
    //ProgressBar progressBar;

    LottieAnimationView animationView;

    long date = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/MM , yyyy EEEE");
    SimpleDateFormat sdfday = new SimpleDateFormat("EEEE");
    String dateString = sdf.format(date);
    String day = sdfday.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.your_title);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // progressBar= (ProgressBar) findViewById(R.id.progressBar);

        animationView = (LottieAnimationView) findViewById(R.id.animation_view);



        //drawerLayout = findViewById(R.id.nav_view);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        NavigationView navigationView=findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(this);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Homefragment());


//This drawable shows a Hamburger icon when drawer is closed and an arrow when drawer is open. It animates between these two states as the drawer opens.
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//
//        if(savedInstanceState==null)
//        {
//            navigationView.setCheckedItem(R.id.home);
//        }




        ActionBar actionBar;
        actionBar = getSupportActionBar();

//         Define ColorDrawable object and parse color
//         using parseColor method
//         with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#32a8a4"));

//         Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        animationView.playAnimation();

        //animation
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        mRecyclerView.setLayoutAnimation(animation);
        TextView tv = (TextView) findViewById(R.id.dateTimeDisplay);
        tv.setText(dateString);
        //making the progressbar visible
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
//        progressBar.setVisibility(View.VISIBLE);
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
    private void parseJSON() {
       String url="https://script.google.com/a/rku.ac.in/macros/s/AKfycby506u5XB7HQj7TMY_ZbRRKWtww1N85bg4XLDeVFJKjP33EbPYj/exec?id=1iW4AauD3siDPm08eiyDRXuvRnnG9PDi8zepXKeIXFt8&sheet=TimeTable&header=1&row=2&Class=6BCA&Day=";
       String url_rk=url.concat(day);
       Log.i("days",url_rk);
        //for sunday
       //String url="https://script.google.com/a/rku.ac.in/macros/s/AKfycby506u5XB7HQj7TMY_ZbRRKWtww1N85bg4XLDeVFJKjP33EbPYj/exec?id=1iW4AauD3siDPm08eiyDRXuvRnnG9PDi8zepXKeIXFt8&sheet=TimeTable&header=1&row=2&Class=6BCA&Day=Sunday";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_rk, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //progressBar.setVisibility(View.INVISIBLE);
                        animationView.cancelAnimation();
                        animationView.setVisibility(View.GONE);
                        //lottieCatThrowsCup.setVisibility(View.INVISIBLE);
                        Log.i("jarr",response.toString());
                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            JSONArray jsonArray = obj.getJSONArray("records");
                            if(jsonArray.length()>0)
                            {
                                mExampleAdapter = new ExampleAdapter(MainActivity.this, jsonArray);
                                mRecyclerView.setAdapter(mExampleAdapter);
                                mExampleAdapter.notifyDataSetChanged();
                            }
                            else
                            {
                                Intent ganesh = new Intent(MainActivity.this,Holiday.class);
                                Toast.makeText(MainActivity.this, "Hurrey its Holiday", Toast.LENGTH_SHORT).show();
                                startActivity(ganesh);
                                finish();

                            }
//                            List<String> filtered = new ArrayList<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject obj1 = jsonArray.getJSONObject(i);
////                                String id = obj.getString("id_estado");
//                                if ("Monday".equals(obj1.getString("Day"))) {
//                                    filtered.add(obj1.getJSONObject(""));
//                                }
//                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Intent ganesh = new Intent(MainActivity.this,Holiday.class);
//                startActivity(ganesh);
                Log.i("jarr",error.toString());
                error.printStackTrace();

            }
        });
        mRequestQueue.add(request);


    }
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//        switch (menuItem.getItemId())
//        {
////            case R.id.home:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Homefragment()).commit();
////                break;
////            case R.id.user:
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Userfragment()).commit();
////                break;
//            case R.id.camera:
//                Intent intent1=new Intent(this,Dev_info.class);
//                startActivity(intent1);
//                break;
//            case R.id.send:
//                Intent intent=new Intent(this,account_Details.class);
//                startActivity(intent);
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//
//
//
//        return true;
//    }

    //@Override
//    public void onBackPressed() {
//
//        if(drawerLayout.isDrawerOpen(GravityCompat.START))
//        {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        else
//        {
//            super.onBackPressed();
//        }
//
//    }
}



