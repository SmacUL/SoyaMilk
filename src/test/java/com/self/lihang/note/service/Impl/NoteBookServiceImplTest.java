package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.Mapper.NoteBooksMapper;
import com.self.lihang.note.Mapper.UserMapper;
import com.self.lihang.note.bean.NoteBook;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserNoteAndBookModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteBookServiceImplTest {

    @Autowired
    NoteBooksMapper noteBooksMapper;
    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;
    @Autowired
    HttpSession session;
    @Test
    public void userAddNewNoteBook() {
        NoteBook noteBook  = new NoteBook();
//        noteBook.setNotebookId(32);
        noteBook.setAuthor_id(3);
        noteBook.setBelongBook_id(10);
        noteBook.setContent("asdfasdfasdf");
        noteBook.setTitle("werwer");
//        noteBook.setCreateTime(new Timestamp(new Date().getTime()));
//        noteBook.setPublicTime(new Timestamp(new Date().getTime()));
//        noteBook.setPublic(0);

        noteBooksMapper.addUserNoteBoook(noteBook);
    }

    @Test
    public void getNotebooksByFolderId() {
        List<NoteBook> results = noteBooksMapper.selectByFolderId(2);
        System.out.println(results);
    }

    @Test
    public void getNoteInfo() {
        UserNoteAndBookModel userNoteAndBookModel = new UserNoteAndBookModel();
        userNoteAndBookModel.setNote(noteBooksMapper.selectByPrimaryKey(13));
        userNoteAndBookModel.setBook(bookMapper.selectByPrimaryKey(userNoteAndBookModel.getNote().getBelongBook_id()));
        userNoteAndBookModel.setUser(userMapper.selectByPrimaryKey(userNoteAndBookModel.getNote().getAuthor_id()));

        User u = (User)session.getAttribute("user");
        System.out.println(u.getUserId());
        if (u.getUserId().equals(userNoteAndBookModel.getUser().getUserId())) {
            userNoteAndBookModel.setSelf(true);
        } else {
            userNoteAndBookModel.setSelf(false);
        }

    }
}