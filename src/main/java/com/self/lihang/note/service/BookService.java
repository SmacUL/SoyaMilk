package com.self.lihang.note.service;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.pageModel.BookInfo;
import com.self.lihang.note.pageModel.Page;

import java.util.List;

public interface BookService {

    Book getBook(Integer bookId);

    boolean numberPlus(Integer bookId);

    boolean numberMinus(Integer bookId);

    Page getBooks(Integer subdivisionId,Page page);

    Page getMyFollowBook(Integer userId,Page page);

    boolean followBook(Integer bookId);

    boolean unfollowBook(Integer bookId);

    boolean isfollowBook(Integer bookId);

    BookInfo getBookInfo(Integer bookId);

    List getHotBook();
}
