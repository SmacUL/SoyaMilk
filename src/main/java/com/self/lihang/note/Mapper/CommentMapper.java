package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Comment;
import com.self.lihang.note.pageModel.CommentAndUserModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {

    /**
     * 删除评论
     * */
    @Select("delete from Comment where collectionId=#{collectionId}")
    int deleteByPrimaryKey(Integer commentId);

    /**
     *  添加评论
     * */
    @Insert("insert into Comment(user_id,notebook_id,comment) values (#{user_id},#{notebook_id},#{comment})")
    int insertComment(Comment record);

    /***
     * 应该不会用到
     */
    @Select("select * from Comment where commentId = #{commentId}")
    Comment selectByPrimaryKey(Integer commentId);

    /**
     * 查找关于该笔记的评论
     * */
    @Select("select * from Comment where notebook_id=#{notebook_id}")
    List<CommentAndUserModel> selectByNotebookIdPage(Integer notebook_id, Page page);


}
