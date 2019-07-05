package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.FollowBook;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface FollowBookMapper {

    @Insert("insert into followBook(user_id,book_id,follow_time) values(#{userId},#{bookId},#{time})")
    int addFollow(Integer userId, Integer bookId, Timestamp time);

    @Delete("delete from followBook where user_id = #{userId} and book_id = #{bookId}")
    int deleteFollow(Integer userId,Integer bookId);

    /**
     * 查找我是否关注该书
     * */
    @Select("select * from followBook where user_id = #{userId} and book_id = #{bookId}")
    FollowBook findFollow(Integer userId,Integer bookId);

    @Select("select count(*) from followBook where user_id = #{userId}")
    int findMyFollowCount(Integer userId);

    /**
     * 查询我关注的书
     * */
    @Select("select * from followBook where user_id = #{userId}")
    List<FollowBook> findMyFollowPage(Integer userId, Page page);

    @Select("select count(*) from followBook where book_id = #{bookId}")
    int findFollowBookPeopleCount(Integer bookId);


    @Select("select book_id from followBook group by book_id order by count(*) desc limit 10")
    List<FollowBook> getHotBookTop10();

}
