package com.fakechat.practice.service.message;


public class PingMessage extends Message {
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
