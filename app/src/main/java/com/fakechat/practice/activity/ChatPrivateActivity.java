package com.fakechat.practice.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fakechat.practice.R;
import com.fakechat.practice.adapter.MsgListAdapter;
import com.fakechat.practice.adapter.MsgListItem;
import com.fakechat.practice.api.LoginApi;
import com.fakechat.practice.api.SavePMsgApi;
import com.fakechat.practice.config.MsgType;
import com.fakechat.practice.model.Pmsg;
import com.fakechat.practice.service.IMClient;
import com.fakechat.practice.utils.StringUtil;
import com.fakechat.practice.utils.TimeUtil;
import com.fakechat.practice.utils.ToastUtil;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatPrivateActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ChatPrivateActivity";
    private RecyclerView recyclerView;
    private MsgListAdapter adapter;
    private TextView newMsgTip;
    private List<MsgListItem> itemList = new ArrayList<>();
    private EditText messageEditText;
    // 更多功能按钮（加号）
    private View moreFunctionButtonInInputBar;
    // 表情选择按钮
    // 发送消息按钮
    private View sendMessageButtonInInputBar;
    private IMClient imClient;
    String toUser = "";
    String fromUser = "";
    int fUid = -1;
    int tUid = -1;
    private static final int UPDATE_MESSAGE = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_MESSAGE:
                    String content = msg.getData().getString("content");
                    String username = msg.getData().getString("username");
                    itemList.add(new MsgListItem(username, content, false));
                    adapter.notifyDataSetChanged();
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_private_main;
    }

    @Override
    protected void initView() {

        recyclerView = findViewById(R.id.messageListView);
        newMsgTip = findViewById(R.id.tv_newMsgTip);
        messageEditText = findViewById(R.id.editTextMessage);
        moreFunctionButtonInInputBar = findViewById(R.id.buttonMoreFunctionInText);
        sendMessageButtonInInputBar = findViewById(R.id.buttonSendMessage);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new MsgListAdapter(itemList);
        recyclerView.setAdapter(adapter);

        imClient = IMClient.getInstance();
    }

    @Override
    protected void setView() {

        Intent getIntent = getIntent();
        String toUsername = getIntent.getStringExtra("toUsername");
        tUid = getIntent.getIntExtra("tUid", -1);

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "nothing");
        fUid = sharedPreferences.getInt("uid", -1);
        fromUser = username;
        toUser = toUsername;
    }

    @Override
    protected void initListener() {
        newMsgTip.setOnClickListener(this);
        sendMessageButtonInInputBar.setOnClickListener(this);
        messageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 切换 "发送" / "加号"
                checkSendButtonEnable();
            }
        });

        imClient.setReceiveMessageListener(msg -> {
            Message receiveMessage = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("content", msg.getContent());
            bundle.putString("username", msg.getFrom());
            receiveMessage.setData(bundle);
            receiveMessage.what = UPDATE_MESSAGE;
            handler.sendMessage(receiveMessage);
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_newMsgTip:
                adapter.notifyDataSetChanged();
                break;
            case R.id.buttonSendMessage:
                try {
                    onClickSendButton();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 点击发送按钮
     */
    private void onClickSendButton() throws Exception {
        String text = messageEditText.getText().toString();
        imClient.sendMessage(fromUser, toUser, text);
        itemList.add(new MsgListItem(fromUser, text, true));
        adapter.notifyDataSetChanged();
        messageEditText.setText("");
        checkSendButtonEnable();
        Pmsg pmsg = new Pmsg();
        pmsg.setFid(fUid);
        pmsg.setTid(tUid);
        pmsg.setContent(text);
        pmsg.setContentType(MsgType.text.getValue());
        pmsg.setCreatTime(TimeUtil.dateLong2String());

        EasyHttp.post(this)
                .api(new SavePMsgApi()
                        .setParams(JSON.parseObject(JSON.toJSONString(pmsg), HashMap.class)))
                .request(new HttpCallback<String>(null) {
                    @Override
                    public void onSucceed(String result) {
                        System.out.println(result);

                    }
                });



    }

    /**
     * 如果输入栏有文字，则显示"发送"，否则显示"加号"
     */
    private void checkSendButtonEnable() {
        String textMessage = messageEditText.getText().toString();
        if (!TextUtils.isEmpty(StringUtil.removeBlanks(textMessage)) && messageEditText.hasFocus()) {
            sendMessageButtonInInputBar.setVisibility(View.VISIBLE);
            moreFunctionButtonInInputBar.setVisibility(View.GONE);
        } else {
            moreFunctionButtonInInputBar.setVisibility(View.VISIBLE);
            sendMessageButtonInInputBar.setVisibility(View.GONE);
        }
    }
}
