package com.fakechat.practice.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fakechat.practice.R;
import com.fakechat.practice.api.LoginApi;
import com.fakechat.practice.utils.ToastUtil;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;


/**
 * Created by FungWah on 2018/2/26.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private static final String TAG = "LoginActivity";

    private SimpleDraweeView avatarSdv;
    private EditText usernameEt;
    private EditText passwordEt;

    private TextView switchTv;

    private Button loginBtn;

    private TextView usernameTipsTv;
    private TextView passwordTipsTv;

    private String usernameStr = "";
    private String passwordStr = "";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome_login;
    }

    @Override
    protected void initView() {
        avatarSdv = findView(R.id.avatar_sdv);
        usernameEt = findView(R.id.username_et);
        passwordEt = findView(R.id.password_et);
        switchTv = findView(R.id.switch_tv);
        loginBtn = findView(R.id.login_btn);

        usernameTipsTv = findView(R.id.username_tips_tv);
        passwordTipsTv = findView(R.id.password__tips_tv);
        usernameEt.setText("jake");
        passwordEt.setText("123");


    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        switchTv.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        avatarSdv.setOnClickListener(this);

        usernameEt.addTextChangedListener(this);
        passwordEt.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar_sdv:

                break;
            case R.id.switch_tv:

                break;
            case R.id.login_btn:
                login();
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        usernameStr = usernameEt.getText().toString();
//        if (usernameEt.isFocused()) {
//            handler.sendEmptyMessage(0);
//        }
        passwordStr = passwordEt.getText().toString();
//        loginBtn.setEnabled(checkMessageEnable());
    }

    private void dismissTips(TextView textView) {
        textView.setVisibility(View.GONE);
    }

    private void showTips(TextView textView, String tips) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(tips);
    }

    private boolean checkMessageEnable() {
        boolean usernameFlag = false;
        boolean passwordFlag = false;

        //鉴定 username
        if (usernameStr != null) {
            if (usernameStr.length() >= 6) {
                usernameFlag = true;
                dismissTips(usernameTipsTv);
            } else {
                if (usernameStr.length() >= 1)
                    showTips(usernameTipsTv, "用户名少于6个字符");
            }
        }

        //鉴定 password
        if (passwordStr != null) {
            if (passwordStr.length() >= 6) {
                passwordFlag = true;
                dismissTips(passwordTipsTv);
            } else {
                if (passwordStr.length() >= 1)
                    showTips(passwordTipsTv, "密码少于6个字符");
            }
        }
        return usernameFlag && passwordFlag;
    }


    private void login() {
        usernameStr = usernameEt.getText().toString();
        passwordStr = passwordEt.getText().toString();
        EasyHttp.post(this)
                .api(new LoginApi()
                        .setParams(usernameStr, passwordStr))
                .request(new HttpCallback<String>(null) {
                    @Override
                    public void onSucceed(String result) {
                        System.out.println(result);
                        JSONObject jsonObject = (JSONObject) JSON.parse(result);
                        int status = (int) jsonObject.get("status");
                        if (status == 1) {
                            Intent intent = new Intent(LoginActivity.this,MainFrameworkActivity.class);
                            intent.putExtra("username", usernameStr);
                            intent.putExtra("uid", (int)jsonObject.get("data"));
                            startActivity(intent);
                            finish();
                        }
                        ToastUtil.showShort(jsonObject.get("message").toString());
                    }
                });

    }

}
