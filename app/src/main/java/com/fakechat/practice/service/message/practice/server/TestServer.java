package com.fakechat.practice.service.message.practice.server;

import androidx.annotation.NonNull;


public class TestServer extends ReleaseServer {

    @NonNull
    @Override
    public String getHost() {
        return "https://www.wanandroid.com/";
    }
}