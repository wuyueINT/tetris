package com.example.myapplication;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler {
    private HandlerCallback callback;

    public MyHandler(HandlerCallback callback){
        this.callback = new WeakReference<>(callback).get();
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        if (callback==null){
            return;
        }
        callback.handleMessage(msg);
    }

    public interface HandlerCallback{
        void handleMessage(Message msg);
    }
}
