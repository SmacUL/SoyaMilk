package com.self.lihang.note.pageModel;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.NoteBook;

public class NoteBookAndBookModel extends NoteBook {

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
