package com.fakechat.practice.model;

import java.io.File;


public class UserInfo {

    private File AvatarFile;

    private String nickname = "nickname";

    private String userName = "userName";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public File getAvatarFile() {
        return AvatarFile;
    }

    public void setAvatarFile(File avatarFile) {
        AvatarFile = avatarFile;
    }
}
