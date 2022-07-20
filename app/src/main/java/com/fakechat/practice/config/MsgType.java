package com.fakechat.practice.config;

public enum MsgType {
    text(1, "文本"),
    image(2, "图片"),
    audio(3, "音频"),
    video(4, "视频"),
    file(5, "文件"),
    tip(6, "提醒消息"),
    blog(7, "博客"),
    card(8, "名片"),
    call(9, "电话"),
    redPaper(10, "红包"),
    receiveRedPaper(11, "领取红包"),
    groupApply(13, "群申请"),
    location(14,"位置信息"),
    unknown(-1, "未知");

    private final int value;
    private final String desc;

    MsgType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MsgType ofValue(int value) {
        MsgType[] enums = values();
        for (MsgType anEnum : enums) {
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
