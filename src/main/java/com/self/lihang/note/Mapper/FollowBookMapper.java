package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.FollowBook;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FollowBookMapper {

    @Insert("insert into followBook(user_id,book_id,follow_time) values(#{userId},#{bookId},#{time})")
    int addFollow(@Param("userId") Integer userId, @Param("bookId")Integer bookId, @Param("time") Timestamp time);

    @Delete("delete from followBook where user_id = #{userId} and book_id = #{bookId}")
    int deleteFollow(@Param("userId")Integer userId,@Param("bookId")Integer bookId);

    /**
     * 查找我是否关注该书
     * */
    @Select("select * from followBook where user_id = #{userId} and book_id = #{bookId}")
    FollowBook findFollow(@Param("userId") Integer userId,@Param("bookId") Integer bookId);

    @Select("select count(*) from followBook where user_id = #{userId}")
    int findMyFollowCount(Integer userId);

    /**
     * 查询我关注的书
     * */
    @Select("select * from followBook where user_id = #{userId}")
    List<FollowBook> findMyFollowPage(@Param("userId") Integer userId,@Param("page") Page page);

    @Select("select count(*) from followBook where book_id = #{bookId}")
    int findFollowBookPeopleCount(@Param("bookId") Integer bookId);


    @Select("select book_id from followBook group by book_id order by count(*) desc limit 10")
    List<FollowBook> getHotBookTop10();

}
