package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.Comment;

public class CommentAndUserModel extends Comment{

    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
