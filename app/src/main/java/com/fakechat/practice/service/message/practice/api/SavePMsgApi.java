package com.fakechat.practice.service.message.practice.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import java.util.HashMap;


public final class SavePMsgApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return "/pmsg/save";
    }


    private HashMap<String, Object> map;


    public SavePMsgApi setParams(HashMap map) {
        this.map = map;
        return this;
    }

    public final static class Bean {


    }

}