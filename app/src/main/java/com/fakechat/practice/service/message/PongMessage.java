package com.fakechat.practice.service.message;

public class PongMessage extends Message {
    @Override
    public int getMessageType() {
        return PongMessage;
    }
}
