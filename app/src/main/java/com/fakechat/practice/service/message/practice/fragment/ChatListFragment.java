package com.fakechat.practice.service.message.practice.fragment;

import android.view.View;
import android.widget.TextView;

import com.fakechat.practice.R;


public class ChatListFragment extends BaseFragment implements View.OnClickListener {

    private TextView chatTv;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_chat_list;
    }

    @Override
    protected void initView(View parent) {
        chatTv = findView(R.id.chat_tv);

    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        chatTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_tv:
                //startActivity(ChatPrivateActivity.class);
                break;

        }
    }
}
