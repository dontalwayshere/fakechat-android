package com.fakechat.practice.service.message.practice.adapter;


import com.fakechat.practice.service.message.practice.model.Friend;

public class ContactListItem {

    private String firstChar;
    private Friend friend;

    public ContactListItem(String firstChar) {
        this.firstChar = firstChar;
        this.friend = null;
    }

    public ContactListItem(Friend userInfo) {
        this.firstChar = null;
        this.friend = userInfo;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
