package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.CommentBook;
import com.self.lihang.note.pageModel.CommentBookAndUserModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentBookMapper {

    @Insert("insert into CommentBook(user_id,book_id,title,comment_time,comment,rate) values(#{user_id},#{book_id},#{title},#{comment_time},#{comment},#{rate})")
    int addBookComemnt(CommentBook commentBook);

    @Select("select * from CommentBook where book_id = #{bookId}")
    List<CommentBookAndUserModel> selectCommentByPage(Integer bookId, Page page);

    @Select("select count(*) from CommentBook where book_id = #{BookId}")
    int selectCommentBookCount(Integer BookId);

    @Select("select count(*) from CommentBook where user_id = #{userId}")
    int selectUserCommentBookCount(Integer userId);

}
