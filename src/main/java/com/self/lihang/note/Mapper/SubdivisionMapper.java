package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Subdivision;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubdivisionMapper {

    /**
     * 删除次级分类
     * */
    @Delete("delete from subdivision where subdivisionId=#{subdivisionId}")
    int deleteByPrimaryKey(Integer subdivisionId);

    /**
     * 添加次级分类
     * */
    @Insert("insert into Subdivision(subdivisionName,belongTop_id) values (#{subdivisionName},#{belongTop_id})")
    int addSubdivision(@Param("subdivisionName")String subdivisionName,@Param("belongTop_id") Integer belongTop_id);

    /**
     * 选择某顶级分类的所有次级分类
     * */
    @Select("select * from Subdivision where belongTop_id=#{belongTopId}")
    List<Subdivision> selectByBelongTopId(Integer belongTopId);

    /**
     * 修改次级分类名
     * */
    @Update("update subdivision set subdivisionName=#{subdivisionName} where subdivisionId=#{subdivisionId}")
    int updateByPrimaryKey(Subdivision record);
}