package com.example.timetable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;

public class MyUtil {
    public static String USER_DATA = "https://script.google.com/a/rku.ac.in/macros/s/AKfycby506u5XB7HQj7TMY_ZbRRKWtww1N85bg4XLDeVFJKjP33EbPYj/exec?id=12WQI2GvwfzQioU31_9gYuaFU9ykp85FROGtD2HJv-nk&sheet=DEC2020&header=1&row=2&Email=";
    public static final String BRANCH_TT = "https://script.google.com/a/rku.ac.in/macros/s/AKfycby506u5XB7HQj7TMY_ZbRRKWtww1N85bg4XLDeVFJKjP33EbPYj/exec?id=1iW4AauD3siDPm08eiyDRXuvRnnG9PDi8zepXKeIXFt8&sheet=TimeTable&header=1&row=2&Class=";
    public static JSONArray jsonArray = null;
   // public static String USER_DATA = "https://script.google.com/a/rku.ac.in/macros/s/AKfycby506u5XB7HQj7TMY_ZbRRKWtww1N85bg4XLDeVFJKjP33EbPYj/exec?id=12WQI2GvwfzQioU31_9gYuaFU9ykp85FROGtD2HJv-nk&sheet=DEC2020&header=1&row=2&Email=";

    //*******************"For checking network connection"*******************
    public static boolean isOnline(Context context) {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
//            Log.d("wifick", String.valueOf(nInfo.getType() == ConnectivityManager.TYPE_WIFI));
//            Log.d("wifick", String.valueOf(nInfo.getType() == ConnectivityManager.TYPE_MOBILE));
            Log.d("NetworkCk", String.valueOf(nInfo.getState()));
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
//        NetworkInfo mobileInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isNetworkOnline1(Context context) {
        boolean isOnline = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());  // need ACCESS_NETWORK_STATE permission
            isOnline = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOnline;
    }

}
