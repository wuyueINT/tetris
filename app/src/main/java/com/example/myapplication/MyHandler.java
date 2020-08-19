package com.example.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler {
    private Activity activity;

    public MyHandler(Activity activity){
        this.activity = new WeakReference<>(activity).get();
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (activity==null){
            return;
        }
        super.handleMessage(msg);
    }

}
