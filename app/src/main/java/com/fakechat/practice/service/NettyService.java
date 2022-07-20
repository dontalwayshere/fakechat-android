package com.fakechat.practice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class NettyService extends Service   {



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String username = intent.getStringExtra("username");
        new Thread(() -> {
            IMClient.start(username);
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
