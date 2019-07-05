package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.CommentBook;

public class CommentBookAndUserModel extends CommentBook {

    private UserModel pageUser;

    public UserModel getPageUser() {
        return pageUser;
    }

    public void setPageUser(UserModel pageUser) {
        this.pageUser = pageUser;
    }
}
