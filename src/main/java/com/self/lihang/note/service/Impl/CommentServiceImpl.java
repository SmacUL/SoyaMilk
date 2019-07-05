package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.*;
import com.self.lihang.note.bean.Comment;
import com.self.lihang.note.bean.CommentBook;
import com.self.lihang.note.bean.Recomment;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.CommentAndUserModel;
import com.self.lihang.note.pageModel.CommentBookAndUserModel;
import com.self.lihang.note.pageModel.RecommentAndUserModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.pageModel.UserModel;
import com.self.lihang.note.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    HttpSession session;

    @Autowired
    CommentBookMapper commentBookMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RecommentMapper recommentMapper;

    /**
     * 添加 图书评论
     * */
    @Transactional
    public boolean addComment(CommentBook commentBook){
        try {
            User u = (User) session.getAttribute("user");
            commentBook.setUser_id(u.getUserId());
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            commentBook.setComment_time(timestamp);
            commentBookMapper.addBookComemnt(commentBook);
            double rate = bookMapper.selectBookRate(commentBook.getBook_id());
            rate = (rate + 0.1 * commentBook.getRate())/1.1;
            bookMapper.updataBookRate(rate,commentBook.getBook_id());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 分页获取 图书评论
     * */
    @Transactional
    public Page loadCommentBook(Integer bookId,Page page){

        // 取出分页评论
        List<CommentBookAndUserModel> commentBookModels =  commentBookMapper.selectCommentByPage(bookId,page);
        for (CommentBookAndUserModel commentBookModel:commentBookModels){
            UserModel userModel = userMapper.selectUserModelByPrimaryKey(commentBookModel.getUser_id());
            commentBookModel.setPageUser(userModel);
        }
        page.setList(commentBookModels);
        return page;
    }

    /**
     * 添加 笔记评论
     * */
    @Transactional
    public boolean addNoteComment(Comment comment){
        try {
            User u = (User)session.getAttribute("user");
            comment.setUser_id(u.getUserId());
            commentMapper.insertComment(comment);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 分页获取 笔记评论
     * */
    @Transactional
    public Page getCommentAndUser(Integer noteId,Page page){

        List<CommentAndUserModel> commentAndUserModels = commentMapper.selectByNotebookIdPage(noteId,page);
        for (CommentAndUserModel commentAndUserModel:commentAndUserModels){
            commentAndUserModel.setUser(userMapper.selectUserModelByPrimaryKey(commentAndUserModel.getUser_id()));
        }
        page.setList(commentAndUserModels);
        return page;
    }

    @Transactional
    public boolean addRecomment(Recomment recomment){
        try {
            User u = (User)session.getAttribute("user");
            recomment.setUser_id(u.getUserId());
            recommentMapper.addRecomment(recomment);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    public Page getRecomment(Integer comment_id,Page page){

        List lists = recommentMapper.selectRecommentByIdAndPage(comment_id,page);
        for (RecommentAndUserModel recommentAndUserModel :(List<RecommentAndUserModel>) lists){
            recommentAndUserModel.setUser(userMapper.selectUserModelByPrimaryKey(recommentAndUserModel.getUser_id()));
        }
        page.setList(lists);
        return page;
    }
}
