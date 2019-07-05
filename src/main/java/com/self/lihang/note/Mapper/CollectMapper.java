package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CollectMapper {

    /**
     * 删除
     * */
    @Select("delete from Collect where collectionId=#{collectionId}")
    int deleteByPrimaryKey(Integer collectionId);

    /**
     * 收藏到文件夹
     * */
    @Insert("insert into Collect(folder_id,notebook_id,collectionTime) values (#{folder_id},#{notebook_id},#{collectionTime})")
    int insertCollect(Collect record);

    @Delete("delete from Collect where folder_id = #{folder_id} and notebook_id = #{notebook_id}")
    int deleteCollect(Collect collect);

    /**
     * 返回值的作用：主要通过 collectionId 找的 notebook_id
     * */
    @Select("select * from Collect where collectionId=#{collectionId}")
    Collect selectByPrimaryKey(Integer collectionId);

    /**
     * 将收藏的笔记移动文件夹
     * */
    @Update("update Collect set folder_id = #{folder_id} ")
    int updateFolderByPrimaryKey(Collect record);

    @Select("select count(*) from Collect where folder_id=#{folder_id}")
    int selectCountByFolderId(Integer folder_id);

}