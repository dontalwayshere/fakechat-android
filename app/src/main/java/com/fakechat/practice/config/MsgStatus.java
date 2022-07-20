package com.fakechat.practice.config;

public enum MsgStatus {
    draft(1),
    sending(2),
    success(3),
    fail(4),
    read(5),
    unread(6);

    private int value;

    private MsgStatus(int var3) {
        this.value = var3;
    }

    public static MsgStatus ofValue(int value) {
        MsgStatus[] enums = values();
        for (MsgStatus anEnum : enums) {
            if (anEnum.getValue() == value) {
                return anEnum;
            }
        }
        return null;
    }

    public final int getValue() {
        return this.value;
    }
}
