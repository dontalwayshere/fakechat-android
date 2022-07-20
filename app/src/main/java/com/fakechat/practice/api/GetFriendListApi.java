package com.fakechat.practice.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;


public final class GetFriendListApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return "/user/getFriendList";
    }


    private String username;


    public GetFriendListApi setParams(String username ) {
        this.username = username;
        return this;
    }


    public final static class Bean {


        }

}