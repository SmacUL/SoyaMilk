package com.self.lihang.note.bean;

public class User {

    private Integer userId;
    private String account;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", selfStyle='" + selfStyle + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private String password;
    private boolean admin;
    private String avatarUrl;
    private String selfStyle;
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
