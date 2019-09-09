package com.self.lihang.note.controller;

import com.self.lihang.note.bean.CommentBook;
import com.self.lihang.note.bean.NoteBook;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserNoteAndBookModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/note")
public class NoteBookController {

    @Autowired
    NoteBookService noteBookService;

    @Autowired
    HttpSession session;

    @RequestMapping("/getHotNoteBook")
    public List<NoteBook> getHotNoteBook(Integer bookId){
        return noteBookService.getHotNote(bookId);
    }

    @RequestMapping("/createNoteBook")
    public boolean createNoteBook(Integer bookId){
        User user = (User) session.getAttribute("user");
        return noteBookService.userCreateNotebook(user,bookId);
    }

    @RequestMapping("/getNotebookByBookId/{bookId}")
    public Page getNotebookByBookId(@PathVariable Integer bookId , @RequestBody Page page){
        return noteBookService.getNoteBookByBookId(bookId,page);
    }

    @RequestMapping("/getNoteBookByUserId/{userId}")
    public Page getNoteBookByUser(@PathVariable Integer userId, @RequestBody Page page){
        return noteBookService.getNoteBookByuserId(userId,page);
    }

    @RequestMapping("/getUserNoteAndBookModel/{notebookId}")
    public UserNoteAndBookModel getUserNoteAndBookModel(@PathVariable Integer notebookId){
        return noteBookService.getNoteInfo(notebookId);
    }

    /**
     * 用户编辑的新笔记
     */
    @RequestMapping("/userNewNote")
    public boolean userNewNote(@RequestBody NoteBook noteBook) {
//        System.out.println(bookId + "--------------");
        User user = (User) session.getAttribute("user");
//        System.out.println(user.toString());
//        System.out.println(noteBook.toString());
        boolean result = noteBookService.userAddNewNoteBook(user, noteBook);
        System.out.println(result);
        return true;
    };

}
