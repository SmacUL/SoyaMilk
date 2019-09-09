package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.NoteBook;
import com.self.lihang.note.pageModel.NoteBookAndUserModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * q1:注意未公开与默认值之间的关系
 * q2:笔记与书本之间的关系
 * */
@Repository
public interface NoteBooksMapper {

    /**
     * 删除笔记
     * */
    @Delete("delete from Notebooks where notebookId=#{notebookId}")
    int deleteByPrimaryKey(Integer notebookId);

    /**
     * 创建笔记,-----注意默认值
     * */
    @Insert("insert into Notebooks(author_id,editTime,belongBook_id) " +
            "values(#{author_id},#{editTime},#{belongBook_id})")
    int addNoteBoook(NoteBook record);

    /**
     * 2019-09-09
     * 上传用户上传的笔记 SmacUL
     * 这句话有什么问题吗
     * insert into Notebooks(author_id, content, belongBook_id，title)
     */
    @Insert("insert into Notebooks(author_id,content, belongBook_id, title) " +
            "values(#{author_id}, #{content}, #{belongBook_id}, #{title})")
    int addUserNoteBoook(NoteBook record);

    /**
     *  选择某个笔记
     * */
    @Select("select * from Notebooks where notebookId=#{notebookId}")
    NoteBook selectByPrimaryKey(@Param("notebookId") Integer notebookId);

    /**
     *  选择喜欢数量最多的几个笔记
     * */
    @Select("select * from Notebooks where belongBook_id=#{belongBook_id} order by likeNumber desc ")
    List<NoteBook> selectByBookId(@Param("belongBook_id")Integer belongBook_id);

    @Select("select * from Notebooks where belongBook_id=#{belongBook_id}")
    List<NoteBookAndUserModel> selectByBookIdPage(@Param("belongBook_id") Integer belongBook_id, @Param("page") Page page);

    @Select("select * from Notebooks where author_id=#{author_id}")
    List<NoteBookAndUserModel> selectByUserIdPage(@Param("author_id") Integer author_id, @Param("page") Page page);
    /**
     *  修改是否公开
     * */
    @Update("update Notebooks set isPublic = #{isPublic},publicTime=#{publicTime}")
    int updateByPrimaryKey(@Param("isPublic") boolean isPublic,@Param("publicTime")Long publicTime);

    /**
     * 喜欢该笔记
     * */
    @Update("update Notebooks set likeNumber=likeNumber + 1 where notebookId=#{notebookId}")
    int updateNoteNumberByPrimaryKey_in(Integer notebookId);

    /**
     * 更新笔记主要内容
     * */
    @Update("update Notebooks set content=#{content} where user_id = #{user_id}")
    int updateMainContent(@Param("content") String content,@Param("user_id") Integer user_id);


    @Select("select count(*) from Notebooks where author_id = #{author_id}")
    int countMynoteNumber(@Param("author_id") Integer author_id);

    @Select("select count(*) from Notebooks where belongBook_id = #{bookId}")
    int countBookNoteNumber(@Param("bookId") Integer bookId);
}