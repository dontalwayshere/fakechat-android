package com.fakechat.practice.service.message.practice.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;


public final class LoginApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return "/user/login";
    }


    private String username;

    private String pwd;

    public LoginApi setParams(String username , String pwd) {
        this.username = username;
        this.pwd = pwd;
        return this;
    }


    public final static class Bean {


        }

}