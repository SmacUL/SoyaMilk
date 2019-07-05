package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.NoteBook;

public class UserNoteAndBookModel{

    private NoteBook note;
    private Book book;
    private UserModel user;

    public NoteBook getNote() {
        return note;
    }

    public void setNote(NoteBook note) {
        this.note = note;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
