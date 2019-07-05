package com.self.lihang.note.service;

import com.self.lihang.note.bean.Comment;
import com.self.lihang.note.bean.CommentBook;
import com.self.lihang.note.bean.Recomment;
import com.self.lihang.note.pageModel.Page;

public interface CommentService {
    boolean addComment(CommentBook commentBook);
    Page loadCommentBook(Integer bookId,Page page);
    Page getCommentAndUser(Integer noteId,Page page);
    boolean addNoteComment(Comment comment);
    boolean addRecomment(Recomment recomment);
    Page getRecomment(Integer comment_id,Page page);
}
