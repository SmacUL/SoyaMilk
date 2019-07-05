package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.Recomment;

public class RecommentAndUserModel extends Recomment {
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
