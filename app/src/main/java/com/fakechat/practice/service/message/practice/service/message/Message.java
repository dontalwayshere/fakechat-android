package com.fakechat.practice.service.message.practice.service.message;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Message implements Serializable {

    /**
     * 根据消息类型字节，获得对应的消息 class
     * @param messageType 消息类型字节
     * @return 消息 class
     */
    public static Class<? extends Message> getMessageClass(int messageType) {
        return messageClasses.get(messageType);
    }

    private int sequenceId;

    private int messageType;

    public abstract int getMessageType();

    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
    public static final int ChatRequestMessage = 2;
    public static final int ChatResponseMessage = 3;
    public static final int GroupCreateRequestMessage = 4;
    public static final int GroupCreateResponseMessage = 5;
    public static final int GroupJoinRequestMessage = 6;
    public static final int GroupJoinResponseMessage = 7;
    public static final int GroupQuitRequestMessage = 8;
    public static final int GroupQuitResponseMessage = 9;
    public static final int GroupChatRequestMessage = 10;
    public static final int GroupChatResponseMessage = 11;
    public static final int GroupMembersRequestMessage = 12;
    public static final int GroupMembersResponseMessage = 13;
    public static final int PingMessage = 14;
    public static final int PongMessage = 15;
    /**
     * 请求类型 byte 值
     */
    public static final int RPC_MESSAGE_TYPE_REQUEST = 101;
    /**
     * 响应类型 byte 值
     */
    public static final int  RPC_MESSAGE_TYPE_RESPONSE = 102;

    private static final Map<Integer, Class<? extends Message>> messageClasses = new HashMap<>();

    static {
        messageClasses.put(LoginRequestMessage, com.fakechat.practice.service.message.practice.service.message.LoginRequestMessage.class);
        messageClasses.put(LoginResponseMessage, com.fakechat.practice.service.message.practice.service.message.LoginResponseMessage.class);
        messageClasses.put(ChatRequestMessage, com.fakechat.practice.service.message.practice.service.message.ChatRequestMessage.class);
        messageClasses.put(ChatResponseMessage, com.fakechat.practice.service.message.practice.service.message.ChatResponseMessage.class);
        messageClasses.put(GroupCreateRequestMessage, com.fakechat.practice.service.message.practice.service.message.GroupCreateRequestMessage.class);
        messageClasses.put(GroupCreateResponseMessage, com.fakechat.practice.service.message.practice.service.message.GroupCreateResponseMessage.class);
        messageClasses.put(GroupJoinRequestMessage, com.fakechat.practice.service.message.practice.service.message.GroupJoinRequestMessage.class);
        messageClasses.put(GroupJoinResponseMessage, com.fakechat.practice.service.message.practice.service.message.GroupJoinResponseMessage.class);
        messageClasses.put(GroupQuitRequestMessage, com.fakechat.practice.service.message.practice.service.message.GroupQuitRequestMessage.class);
        messageClasses.put(GroupQuitResponseMessage, com.fakechat.practice.service.message.practice.service.message.GroupQuitResponseMessage.class);
        messageClasses.put(GroupChatRequestMessage, com.fakechat.practice.service.message.practice.service.message.GroupChatRequestMessage.class);
        messageClasses.put(GroupChatResponseMessage, com.fakechat.practice.service.message.practice.service.message.GroupChatResponseMessage.class);
        messageClasses.put(GroupMembersRequestMessage, com.fakechat.practice.service.message.practice.service.message.GroupMembersRequestMessage.class);
        messageClasses.put(GroupMembersResponseMessage, com.fakechat.practice.service.message.practice.service.message.GroupMembersResponseMessage.class);
        messageClasses.put(PingMessage, com.fakechat.practice.service.message.practice.service.message.PingMessage.class);
        messageClasses.put(PongMessage, com.fakechat.practice.service.message.practice.service.message.PongMessage.class);

    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
