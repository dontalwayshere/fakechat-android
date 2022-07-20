package com.fakechat.practice;

import android.app.Application;


import androidx.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.fakechat.practice.request.RequestHandler;
import com.fakechat.practice.server.ReleaseServer;
import com.fakechat.practice.utils.SPUtil;
import com.fakechat.practice.utils.ToastUtil;
import com.fakechat.practice.utils.WindowSizeHelper;
import com.hjq.http.EasyConfig;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.request.HttpRequest;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        SPUtil.init(this);
        WindowSizeHelper.init(this);
        Fresco.initialize(this);
        MMKV.initialize(this);


        // 网络请求框架初始化
        IRequestServer server;
//        if (BuildConfig.DEBUG) {
//            server = new TestServer();
//        } else {
//            server = new ReleaseServer();
//        }
        server = new ReleaseServer();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        EasyConfig.with(okHttpClient)
                // 是否打印日志
                //.setLogEnabled(BuildConfig.DEBUG)
                // 设置服务器配置
                .setServer(server)
                // 设置请求处理策略
                .setHandler(new RequestHandler(this))
                // 设置请求参数拦截器
                .setInterceptor(new IRequestInterceptor() {
                    @Override
                    public void interceptArguments(@NonNull HttpRequest<?> httpRequest,
                                                   @NonNull HttpParams params,
                                                   @NonNull HttpHeaders headers) {
                        headers.put("timestamp", String.valueOf(System.currentTimeMillis()));

                    }
                })
                // 设置请求重试次数
                .setRetryCount(1)
                // 设置请求重试时间
                .setRetryTime(2000)
                // 添加全局请求参数
                .addParam("token", "6666666")
                // 添加全局请求头
                //.addHeader("date", "20191030")
                .into();
    }
}
