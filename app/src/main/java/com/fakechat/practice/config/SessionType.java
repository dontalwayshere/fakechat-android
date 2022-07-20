package com.fakechat.practice.config;

public enum SessionType {
    P2P(1),
    Group(2);

    private int value;

    private SessionType(int var3) {
        this.value = var3;
    }

    public static SessionType ofValue(int value) {
        SessionType[] enums = values();
        for (SessionType anEnum : enums) {
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
