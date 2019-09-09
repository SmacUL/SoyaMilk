package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.NoteBooksMapper;
import com.self.lihang.note.bean.NoteBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteBookServiceImplTest {

    @Autowired
    NoteBooksMapper noteBooksMapper;

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
}