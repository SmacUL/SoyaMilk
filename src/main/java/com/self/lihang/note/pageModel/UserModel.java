package com.self.lihang.note.pageModel;

public class UserModel {

    protected Integer userId;
    protected String name;
    protected String avatarUrl;
    protected String selfStyle;
    protected Integer noteNumber;
    protected Integer fansNumber;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSelfStyle() {
        return selfStyle;
    }

    public void setSelfStyle(String selfStyle) {
        this.selfStyle = selfStyle;
    }

    public Integer getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(Integer noteNumber) {
        this.noteNumber = noteNumber;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }
}
