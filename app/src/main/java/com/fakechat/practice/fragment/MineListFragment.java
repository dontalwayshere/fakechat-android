package com.fakechat.practice.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fakechat.practice.R;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fakechat.practice.callback.OnFragmentUpdateListener;
import com.fakechat.practice.model.UserInfo;


public class MineListFragment extends BaseFragment implements View.OnClickListener, OnFragmentUpdateListener {

    private static final String TAG = "MineListFragment";

    private static final int UPDATE_INFO = 0;

    private LinearLayout userInfoLl;
    private LinearLayout walletLl;
    private LinearLayout favorLl;
    private LinearLayout albumLl;
    private LinearLayout cardBagLl;
    private LinearLayout expressionLl;
    private LinearLayout settingLl;

    private SimpleDraweeView avatarSdv;
    private TextView nicknameTv;
    private TextView usernameTv;

    private UserInfo userInfo;



    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_INFO:
                    setView();
                    break;
            }
            return false;
        }
    });

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine_list;
    }

    @Override
    protected void initView(View parent) {


        userInfo = null;

        userInfoLl = findView(R.id.mine_userinfo_ll);
        walletLl = findView(R.id.mine_wallet_ll);
        favorLl = findView(R.id.mine_favor_ll);
        albumLl = findView(R.id.mine_album_ll);
        cardBagLl = findView(R.id.mine_card_bag_ll);
        expressionLl = findView(R.id.mine_expression_ll);
        settingLl = findView(R.id.mine_setting_ll);

        avatarSdv = findView(R.id.mine_userinfo_sdv);
        nicknameTv = findView(R.id.mine_nickname_tv);
        usernameTv = findView(R.id.mine_username_tv);




    }

    @Override
    protected void setView() {
        try {
            avatarSdv.setImageURI("Uri.fromFile(userInfo.getAvatarFile())");
        } catch (Exception e) {
            e.printStackTrace();
        }
        nicknameTv.setText("userInfo.getNickname()");
        usernameTv.setText("userInfo.getUserName()");
    }

    @Override
    protected void initListener() {
        userInfoLl.setOnClickListener(this);
        walletLl.setOnClickListener(this);
        favorLl.setOnClickListener(this);
        albumLl.setOnClickListener(this);
        cardBagLl.setOnClickListener(this);
        expressionLl.setOnClickListener(this);
        settingLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_userinfo_ll:
//                startActivity(MineUserInfoActivity.class);
                getActivity().overridePendingTransition(R.anim.move_in_from_right, R.anim.move_out_from_right);
                break;
            case R.id.mine_wallet_ll:
                break;
            case R.id.mine_favor_ll:
                break;
            case R.id.mine_album_ll:
                break;
            case R.id.mine_card_bag_ll:
                break;
            case R.id.mine_expression_ll:
                break;
            case R.id.mine_setting_ll:
//                startActivity(MineSettingActivity.class);
                getActivity().overridePendingTransition(R.anim.move_in_from_right, R.anim.move_out_from_right);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onFragmentUpdate(Intent intent) {
        handler.sendEmptyMessage(UPDATE_INFO);
    }



}
