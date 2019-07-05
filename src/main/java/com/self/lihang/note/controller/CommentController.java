package com.self.lihang.note.controller;

import com.self.lihang.note.bean.Comment;
import com.self.lihang.note.bean.CommentBook;
import com.self.lihang.note.bean.Recomment;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 发表对图书的评论
     * */
    @RequestMapping("/commentBook")
    public boolean commentBook(@RequestBody CommentBook commentBook){
        return commentService.addComment(commentBook);
    }

    /**
     * 添加 笔记评论
     * */
    @RequestMapping("/sendComment")
    public boolean addNoteComment(@RequestBody Comment comment){
        return  commentService.addNoteComment(comment);
    }

    /**
     * 添加 评论评论
     * */
    @RequestMapping("/addRecomment")
    public boolean addRecomment(@RequestBody Recomment recomment){
        return  commentService.addRecomment(recomment);
    }

    /**
     * 分页加载 图书评论
     * */
    @RequestMapping("/loadComment/{bookId}")
    public Page loadBookComment(@PathVariable Integer bookId,@RequestBody Page page){
        return commentService.loadCommentBook(bookId,page);

    }

    /**
     * 分页获取 笔记评论
     * */
    @RequestMapping("/loadNoteBookCommentByPage/{noteId}")
    public Page loadNoteComment(@PathVariable Integer noteId,@RequestBody Page page){
        return commentService.getCommentAndUser(noteId,page);
    }

    /**
     * 分页获取 评论评论
     * */
    @RequestMapping("/loadRecomment/{comment_id}")
    public Page loadRecomment(@PathVariable Integer comment_id, @RequestBody Page page){
        return commentService.getRecomment(comment_id,page);
    }
}
