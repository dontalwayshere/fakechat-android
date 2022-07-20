package com.fakechat.practice.server;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;


public class ReleaseServer implements IRequestServer {

    @NonNull
    @Override
    public String getHost() {
        return "http://10.0.2.2:83";
    }
}