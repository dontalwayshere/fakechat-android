package com.fakechat.practice.service.message.practice.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fakechat.practice.R;
import com.fakechat.practice.service.message.practice.activity.ChatPrivateActivity;
import com.fakechat.practice.service.message.practice.adapter.ContactListAdapter;
import com.fakechat.practice.service.message.practice.adapter.ContactListItem;
import com.fakechat.practice.service.message.practice.api.GetFriendListApi;
import com.fakechat.practice.service.message.practice.model.Friend;
import com.fakechat.practice.service.message.practice.utils.ToastUtil;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;

import java.util.ArrayList;
import java.util.List;


public class ContactsListFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "ContactsListFragment";

    private static final int UPDATE_FRIEND_LIST = 0;
    private static final int NEW_FRIEND_REQUEST = 1;

    private LinearLayout newFriendLl;
    private LinearLayout groupChatLl;
    private LinearLayout tagLl;
    private LinearLayout subscriptionLl;

    private RecyclerView contactRv;
    private List<ContactListItem> itemList = new ArrayList<>();
    private List<Friend> friendList;
    private ContactListAdapter adapter;

    private RecyclerView.LayoutManager linearManager;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_FRIEND_LIST:
                    updateList();
                    break;
                case NEW_FRIEND_REQUEST:
                    int num = msg.getData().getInt("num");
                    if (num > 0) {
                        newFriendRedDotRl.setVisibility(View.VISIBLE);
                        newFriedRedDotNumTv.setText(num + "");
                    } else {
                        newFriendRedDotRl.setVisibility(View.GONE);
                    }
                    break;
            }
            return false;
        }
    });

    private RelativeLayout newFriendRedDotRl;
    private TextView newFriedRedDotNumTv;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_contacts_list;
    }

    @Override
    protected void initView(View parent) {
        newFriendLl = findView(R.id.contact_new_friend_ll);
        groupChatLl = findView(R.id.contact_group_chat_ll);
        tagLl = findView(R.id.contact_tag_ll);
        subscriptionLl = findView(R.id.contact_subscription_ll);
        contactRv = findView(R.id.contact_list_recycler_view);
        newFriendRedDotRl = findView(R.id.contact_new_friend_request_rl);
        newFriedRedDotNumTv = findView(R.id.contact_new_friend_request_num_tv);

//        ContactManager.getFriendList(new GetUserInfoListCallback() {
//            @Override
//            public void gotResult(int i, String s, List<UserInfo> list) {
//                userInfoList = list;
//                handler.sendEmptyMessage(UPDATE_FRIEND_LIST);
//            }
//        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "nothing");

        EasyHttp.post(this)
                .api(new GetFriendListApi()
                        .setParams(username))
                .request(new HttpCallback<String>(null) {
                    @Override
                    public void onSucceed(String result) {
                        System.out.println(result);
                        JSONObject jsonObject = (JSONObject) JSON.parse(result);
                        int status = (int) jsonObject.get("status");
                        if (status == 1) {
                            friendList = JSONObject.parseArray(jsonObject.get("data").toString(), Friend.class);
                            handler.sendEmptyMessage(UPDATE_FRIEND_LIST);
                        }
                        ToastUtil.showShort(jsonObject.get("message").toString());
                    }
                });

        linearManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new ContactListAdapter(itemList);

    }

    private void updateList() {

        for (int i = 0; i < friendList.size(); i++) {
            itemList.add(new ContactListItem(friendList.get(i)));
        }
        //根据用户名首字母排序
//        Collections.sort(itemList, new FirstCharComparator());
        if (friendList.size() > 0) {
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setView() {
        //初始化刷新红点

        contactRv.setAdapter(adapter);
        contactRv.setLayoutManager(linearManager);
    }

    @Override
    protected void initListener() {
        newFriendLl.setOnClickListener(this);
        groupChatLl.setOnClickListener(this);
        subscriptionLl.setOnClickListener(this);
        tagLl.setOnClickListener(this);

        adapter.setOnItemClickListener((friend) -> {
            String toUsername = friend.getFname();
            Intent intent = new Intent(getActivity(), ChatPrivateActivity.class);
            intent.putExtra("toUsername", toUsername);
            intent.putExtra("tUid", friend.getFid());
            startActivity(intent);

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_new_friend_ll:
//                startActivity(ContactNewFriendActivity.class);
                getActivity().overridePendingTransition(R.anim.move_in_from_right, R.anim.move_out_from_right);
                break;
            case R.id.contact_group_chat_ll:
                break;
            case R.id.contact_tag_ll:
                break;
            case R.id.contact_subscription_ll:
                break;
        }
    }


    /**
     * 刷新好友请求
     *
     * @param num
     */
    public void refreshNewFriendNum(int num) {
        Message newFriendMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        newFriendMessage.setData(bundle);
        newFriendMessage.what = NEW_FRIEND_REQUEST;
        handler.sendMessage(newFriendMessage);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
