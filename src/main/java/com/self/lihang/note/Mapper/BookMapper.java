package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {

    /**
     * 删除书籍
     * */
    @Delete("delete from books where bookId=#{bookId}")
    int deleteByPrimaryKey(@Param("bookId")Integer bookId);

    /**
     * 添加书籍
     * */
    @Insert("insert into books(bookName, bookContributors, chapterNumber, editionNumber,bookCover,belongSubdivision_id,chapterNames ) " +
            "values (#{bookName}, #{bookContributors}, #{chapterNumber}, #{editionNumber},#{bookCover},#{belongSubdivision_id},#{chapterNames})")
    int addBook(@Param("book") Book book);

    /**
     * 查找 书籍的全部内容。
     * */
    @Select("select * from books where bookId=#{bookId}")
    Book selectByPrimaryKey(@Param("bookId")Integer bookId);

    /**
     *  分页查询数量
     * */
    @Select("select count(*) from books where belongSubdivision_id=#{subdivisionId}")
    int selectBookCount(@Param("subdivisionId") Integer subdivisionId);

    /**
     * 查找 某一类subdivision的书籍简介。 分页
     * */   //limit #{startRow}, #{pageSize}
    @Select("select * from books where belongSubdivision_id=#{subdivisionId}")
    List<Book> selectBySubdivisionIdAndPage(@Param("subdivisionId") Integer subdivisionId, @Param("page") Page page);

    /**
     * 创建该书的笔记
     * */
    @Update("update books set bookNumber=bookNumber + 1 where bookId=#{bookId}")
    int updateNoteNumberByPrimaryKey_in(@Param("bookId") Integer bookId);

    /**
     * 删除该书的笔记
     * */
    @Update("update Books set bookNumber=bookNumber - 1 where bookId=#{bookId}")
    int updateNoteNumberByPrimaryKey_de(@Param("bookId")Integer bookId);


    @Update("update books set rate = #{rate} where bookId = #{bookId}")
    int updataBookRate(@Param("rate") Double rate,@Param("bookId")Integer bookId);

    @Select("select rate from books where bookId = #{bookId}")
    double selectBookRate(@Param("bookId")Integer bookId);
}