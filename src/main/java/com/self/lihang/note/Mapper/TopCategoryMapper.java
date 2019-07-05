package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.TopCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface TopCategoryMapper {

    /**
     *  删除该顶级分类
     * */
    @Delete("delete from topCategory where categoryId=#{categoryId}")
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * 添加该顶级分类
     * */
    @Insert("insert into topCategory (categoryName) values ( #{categoryName} )")
    int insert(TopCategory record);

    /**
     * 列举所有顶级分类
     * */
    @Select("select categoryId,categoryName from topCategory")
    List<TopCategory>  selectAll();

    /**
     * 修改顶级分类名
     * */
    @Update("update topCategory set categoryName=#{categoryName} where categoryId=#{categoryId}")
    int updateByPrimaryKey(TopCategory record);
}