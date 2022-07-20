package com.fakechat.practice.service.message.practice.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fakechat.practice.R;


public class ContactListViewHolder extends RecyclerView.ViewHolder {

    public TextView firstCharTv = null;
    public TextView nicknameTv = null;
    public SimpleDraweeView avatarSdv = null;

    public ContactListViewHolder(View itemView) {
        super(itemView);
        if ((int)itemView.getTag() == ContactListAdapter.type.HEAD.ordinal()) {
            firstCharTv = itemView.findViewById(R.id.contact_list_head_first_char_tv);
        } else {
            nicknameTv = itemView.findViewById(R.id.contact_user_nickname_tv);
            avatarSdv = itemView.findViewById(R.id.contact_user_avatar_sdv);
        }
    }
}
