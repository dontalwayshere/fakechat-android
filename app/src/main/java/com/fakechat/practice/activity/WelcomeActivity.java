package com.fakechat.practice.activity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.fakechat.practice.R;


public class WelcomeActivity extends BaseActivity {

    private static final String TAG = "WelcomeActivity";



    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setView() {
        try {
            Thread.sleep(1500);
            startActivity(LoginActivity.class);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {

    }


}
