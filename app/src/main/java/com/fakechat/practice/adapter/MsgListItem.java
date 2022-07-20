package com.fakechat.practice.adapter;


public class MsgListItem {

    private String nikename;

    private String content;

    private boolean isRight;

    public MsgListItem(String nikename, String content,boolean isRight) {
        this.nikename = nikename;
        this.content = content;
        this.isRight = isRight;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }
}
