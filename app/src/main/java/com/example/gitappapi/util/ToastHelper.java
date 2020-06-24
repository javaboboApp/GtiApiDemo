package com.example.gitappapi.util;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.gitappapi.R;

public class ToastHelper {

    public static void showToast(Context context, String msg, int duration){
        android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG).show();

    }
}
