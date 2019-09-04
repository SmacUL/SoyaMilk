package com.self.lihang.note.controller;

/**
 * 获得书的内容
 * 更新（增减）书籍的笔记数量
 * 获取书籍 --- 分页 查询 走马灯
 * 获取书籍的热度信息
 * 是否关注&关注&取消书籍
 */

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.pageModel.BookInfo;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    HttpSession session;

    /**
     * 一本书的内容
     * */
    @RequestMapping(value = "/getBook/{bookId}")
    public Book getBook(@PathVariable Integer bookId){
        Book book = bookService.getBook(bookId);
        return book;
    }

    /**
     * 更新书籍的笔记数量
     * */
    @RequestMapping("/noteNumber_in/{bookId}")
    public boolean increase_Note(@PathVariable Integer bookId){
        return bookService.numberPlus(bookId);
    }

    @RequestMapping("/noteNumber_de/{bookId}")
    public boolean decrease_Note(@PathVariable Integer bookId){
        return bookService.numberMinus(bookId);
    }

    /**
     * 获取书籍 --- 分页 查询 走马灯
     * */
    @RequestMapping("/getBookByPage/{subdivisionId}")
    public Page getSubdivisionBooks(@PathVariable Integer subdivisionId, @RequestBody Page page){
        return bookService.getBooks(subdivisionId, page);
    }

    @RequestMapping("/getMyFollowBook/{userId}")
    public Page getUserFollowBook(@PathVariable Integer userId,@RequestBody Page page){
        return bookService.getMyFollowBook(userId,page);
    }

    /**
     * 获取书籍的热度信息
     **/
    @RequestMapping("/getBookInfo/{bookId}")
    public BookInfo getBookInfo(@PathVariable Integer bookId){
        return bookService.getBookInfo(bookId);
    }
    /**
     * 是否关注&关注&取消书籍
     * */
    @RequestMapping("/isfollow/{bookId}")
    public boolean isfollowBook(@PathVariable Integer bookId){
        return bookService.isfollowBook(bookId);
    }
    @RequestMapping("/follow/{bookId}")
    public boolean followBook(@PathVariable Integer bookId){
        return bookService.followBook(bookId);
    }
    @RequestMapping("/unfollow/{bookId}")
    public boolean unfollowBook(@PathVariable Integer bookId){
        return bookService.unfollowBook(bookId);
    }

    @RequestMapping("/getHotBook")
    public List getHotBook(){
        return bookService.getHotBook();
    }
}
