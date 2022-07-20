package com.fakechat.practice.api;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestServer;

import java.io.File;


public final class UpdateImageApi implements IRequestServer, IRequestApi {

    @NonNull
    @Override
    public String getHost() {
        return "https://graph.baidu.com/";
    }

    @NonNull
    @Override
    public String getApi() {
        return "upload/";
    }

    /** 本地图片 */
    private File image;

    public UpdateImageApi(File image) {
        this.image = image;
    }

    public UpdateImageApi setImage(File image) {
        this.image = image;
        return this;
    }
}