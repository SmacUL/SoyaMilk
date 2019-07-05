package com.self.lihang.note.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.Mapper.CommentBookMapper;
import com.self.lihang.note.Mapper.FollowBookMapper;
import com.self.lihang.note.Mapper.NoteBooksMapper;
import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.FollowBook;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.BookInfo;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    FollowBookMapper followBookMapper;

    @Autowired
    HttpSession session;

    @Autowired
    CommentBookMapper commentBookMapper;

    @Autowired
    NoteBooksMapper noteBooksMapper;

    public Book getBook(Integer bookId){
        Book b =  bookMapper.selectByPrimaryKey(bookId);
        return b;
    }

    public boolean numberPlus(Integer bookId){
        if (bookMapper.updateNoteNumberByPrimaryKey_in(bookId) > 0){
            return true;
        }
        return false;
    }

    public boolean numberMinus(Integer bookId){
        if (bookMapper.updateNoteNumberByPrimaryKey_de(bookId) > 0){
            return true;
        }
        return false;
    }

    @Transactional
    public Page getBooks(Integer subdivisionId,Page page){
        page.setList(bookMapper.selectBySubdivisionIdAndPage(subdivisionId,page));
        return page;
    }

    @Transactional
    public Page getMyFollowBook(Integer userId,Page page){
        if (userId == 0){
            User u = (User) session.getAttribute("user");
            userId = u.getUserId();
        }

        List<FollowBook> followBooks =  followBookMapper.findMyFollowPage(userId,page);
        List<Book> books = new ArrayList<>();
        for(FollowBook followBook:followBooks){
            books.add(bookMapper.selectByPrimaryKey(followBook.getBook_id()));
        }
        page.setList(books);
        return page;
    }

    public boolean followBook(Integer bookId){
        try {
            User u = (User) session.getAttribute("user");
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            followBookMapper.addFollow(u.getUserId(),bookId,timestamp);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean unfollowBook(Integer bookId){
        try {
            User u = (User) session.getAttribute("user");
            followBookMapper.deleteFollow(u.getUserId(),bookId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isfollowBook(Integer bookId){
       try {
            User u = (User) session.getAttribute("user");
            FollowBook followBook = followBookMapper.findFollow(u.getUserId(),bookId);
            if (followBook == null){
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Transactional
    public BookInfo getBookInfo(Integer bookId){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setRate(bookMapper.selectBookRate(bookId));
        bookInfo.setFollowPeople(followBookMapper.findFollowBookPeopleCount(bookId));
        bookInfo.setCommentNumber(commentBookMapper.selectCommentBookCount(bookId));
        bookInfo.setNoteNumber(noteBooksMapper.countBookNoteNumber(bookId));
        return bookInfo;
    }

    public List getHotBook(){
        List<FollowBook> list = followBookMapper.getHotBookTop10();
        System.out.println(JSONObject.toJSONString(list));
        List<Book> bookList = new ArrayList<>();
        for (FollowBook book:list){
             bookList.add(bookMapper.selectByPrimaryKey(book.getBook_id()));
        }
        return bookList;
    }

}
