package com.fakechat.practice.api;

import androidx.annotation.NonNull;

import com.fakechat.practice.model.Pmsg;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.model.HttpParams;

import java.util.HashMap;
import java.util.Map;


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