package com.fakechat.practice.service.message.practice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fakechat.practice.R;


public class MsgListViewHolder extends RecyclerView.ViewHolder {



    public View view;
    public  Context context;

    public FrameLayout contentContainer;
    public SimpleDraweeView avatarLeft;
    public SimpleDraweeView avatarRight;
    public TextView nickLeft;
    public TextView nickRight;
    public TextView timeView;
    public TextView timeView2;
    public TextView tv_receipt_left;
    public TextView tv_receipt_right;



    public MsgListViewHolder(View itemView) {
        super(itemView);
        // 内容
//        contentContainer = itemView.findViewById(R.id.message_item_content);
        // 头像
        avatarLeft = itemView.findViewById(R.id.message_item_portrait_left);
        avatarRight = itemView.findViewById(R.id.message_item_portrait_right);
        // 昵称
        nickLeft = itemView.findViewById(R.id.message_item_nickname_left);
        nickRight = itemView.findViewById(R.id.message_item_nickname_right);
        // 时间
        timeView = itemView.findViewById(R.id.message_item_time);
        timeView2 = itemView.findViewById(R.id.message_item_time2);
        // 消息接收状况
        tv_receipt_left = itemView.findViewById(R.id.tv_receipt_left);
        tv_receipt_right = itemView.findViewById(R.id.tv_receipt_right);

    }


}
