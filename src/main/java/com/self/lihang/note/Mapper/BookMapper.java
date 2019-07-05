package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Book;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    /**
     * 删除书籍
     * */
    @Delete("delete from Books where bookId=#{bookId}")
    int deleteByPrimaryKey(Integer bookId);

    /**
     * 添加书籍
     * */
    @Insert("insert into Books(bookName, bookContributors, chapterNumber, editionNumber,bookCover,belongSubdivision_id,chapterNames ) " +
            "values (#{bookName}, #{bookContributors}, #{chapterNumber}, #{editionNumber},#{bookCover},#{belongSubdivision_id},#{chapterNames})")
    int addBook(Book book);

    /**
     * 查找 书籍的全部内容。
     * */
    @Select("select * from Books where bookId=#{booId}")
    Book selectByPrimaryKey(Integer bookId);

    /**
     *  分页查询数量
     * */
    @Select("select count(*) from Books where belongSubdivision_id=#{subdivisionId}")
    int selectBookCount(Integer subdivisionId);

    /**
     * 查找 某一类subdivision的书籍简介。 分页
     * */   //limit #{startRow}, #{pageSize}
    @Select("select * from Books where belongSubdivision_id=#{subdivisionId}")
    List<Book> selectBySubdivisionIdAndPage(Integer subdivisionId, Page page);

    /**
     * 创建该书的笔记
     * */
    @Update("update Books set bookNumber=bookNumber + 1 where bookId=#{bookId}")
    int updateNoteNumberByPrimaryKey_in(Integer bookId);

    /**
     * 删除该书的笔记
     * */
    @Update("update Books set bookNumber=bookNumber - 1 where bookId=#{bookId}")
    int updateNoteNumberByPrimaryKey_de(Integer bookId);

    /**
     *
     * @param rate
     * @param bookId
     * @return
     */
    @Update("update Books set rate = #{rate} where bookId = #{bookId}")
    int updataBookRate(Double rate,Integer bookId);

    @Select("select rate from Books where bookId = #{bookId}")
    double selectBookRate(Integer bookId);
}