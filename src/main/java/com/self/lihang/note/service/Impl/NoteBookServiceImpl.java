package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.Mapper.NoteBooksMapper;
import com.self.lihang.note.Mapper.UserMapper;
import com.self.lihang.note.bean.NoteBook;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.NoteBookAndUserModel;
import com.self.lihang.note.pageModel.UserNoteAndBookModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.NoteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class NoteBookServiceImpl implements NoteBookService {

    @Autowired
    NoteBooksMapper noteBooksMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    HttpSession session;

    public List<NoteBook> getHotNote(Integer BookId){
        return noteBooksMapper.selectByBookId(BookId);
    }

    public boolean userCreateNotebook(User user,Integer bookId){
        NoteBook noteBook = new NoteBook();
        noteBook.setAuthor_id(user.getUserId());
        noteBook.setBelongBook_id(bookId);
        noteBook.setCreateTime(new Timestamp(new Date().getTime()));
        if (noteBooksMapper.addNoteBoook(noteBook) > 0){
            return true;
        }
        return false;
    }

    @Transactional
    public Page getNoteBookByBookId(Integer bookId,Page page){

        List<NoteBookAndUserModel> noteBookAndUserModels = noteBooksMapper.selectByBookIdPage(bookId,page);
        for (NoteBookAndUserModel noteBookAndUserModel:noteBookAndUserModels){
            noteBookAndUserModel.setUserModel(userMapper.selectByPrimaryKey(noteBookAndUserModel.getAuthor_id()));
        }
        page.setList(noteBookAndUserModels);
        return page;
    }

    @Transactional
    public Page getNoteBookByuserId(Integer userId, Page page){

        if (userId == 0){
            User u = (User)session.getAttribute("user");
            userId = u.getUserId();
        }
        page.setList(noteBooksMapper.selectByUserIdPage(userId,page));
        return page;
    }

    @Transactional
    public UserNoteAndBookModel getNoteInfo(Integer notebookId){
        UserNoteAndBookModel userNoteAndBookModel = new UserNoteAndBookModel();
        userNoteAndBookModel.setNote(noteBooksMapper.selectByPrimaryKey(notebookId));
        userNoteAndBookModel.setBook(bookMapper.selectByPrimaryKey(userNoteAndBookModel.getNote().getBelongBook_id()));
        userNoteAndBookModel.setUser(userMapper.selectByPrimaryKey(userNoteAndBookModel.getNote().getAuthor_id()));
        return userNoteAndBookModel;
    }

    /**
     * 2019-09-09 SmacUL
     * 用户添加制定文章的笔记
     * @param user
     * @param noteBook
     * @return
     */
    @Override
    public boolean userAddNewNoteBook(User user, NoteBook noteBook) {
        noteBook.setAuthor_id(user.getUserId());
        if (noteBooksMapper.addUserNoteBoook(noteBook) > 0){
            return true;
        }
        return false;
    }

    /**
     * 2019-09-10
     * 查询指定收藏夹下的笔记
     * @param folderId
     * @return
     */
    @Override
    public List<NoteBook> getNotebooksByFolderId(Integer folderId) {
        List<NoteBook> results = noteBooksMapper.selectByFolderId(folderId);
        return results;
    }

}
