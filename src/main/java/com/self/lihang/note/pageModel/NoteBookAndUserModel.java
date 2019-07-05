package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.NoteBook;

public class NoteBookAndUserModel extends NoteBook {

    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
