package com.fakechat.practice.service.message.practice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.fakechat.practice.R;
import com.fakechat.practice.service.message.practice.utils.TimeUtil;

import java.util.List;


public class MsgListAdapter extends RecyclerView.Adapter<MsgListViewHolder> {




    private static final String TAG = "MsgListAdapter";

    private List<MsgListItem> msgListItems;

    public MsgListAdapter(List<MsgListItem> list) {
        this.msgListItems = list;
    }

    @Override
    public MsgListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MsgListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false));
    }


    @Override
    public void onBindViewHolder(MsgListViewHolder holder, int position) {
        MsgListItem item = msgListItems.get(position);
        String timeText = TimeUtil.getShowTime(System.currentTimeMillis(), false);
        if (item.isRight()) {
            holder.nickRight.setText(item.getNikename());
            holder.tv_receipt_right.setText(item.getContent());
            holder.nickRight.setVisibility(View.GONE);
            holder.nickLeft.setVisibility(View.GONE);
            holder.tv_receipt_left.setVisibility(View.GONE);
            holder.timeView.setVisibility(View.GONE);
            holder.timeView2.setText(String.valueOf(timeText));
        } else {
            holder.nickLeft.setText(item.getNikename());
            holder.tv_receipt_left.setText(item.getContent());
            holder.nickRight.setVisibility(View.GONE);
            holder.tv_receipt_right.setVisibility(View.GONE);
            holder.timeView.setText(String.valueOf(timeText));
            holder.timeView2.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return msgListItems.size();
    }
}
