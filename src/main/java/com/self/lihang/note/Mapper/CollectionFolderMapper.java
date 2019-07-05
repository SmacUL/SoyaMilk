package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.CollectionFolder;
import com.self.lihang.note.pageModel.CollectionFolderModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CollectionFolderMapper {


    /**
     * 删除文件夹
     * */
    @Delete("delete from collectionFolder where folderId=#{folderId}")
    int deleteByPrimaryKey(Integer folderId);

    /**
     * 添加文件夹
     * */
    @Insert("insert into collectionFolder(folderName,user_id) values(#{folderName},#{user_id}) ")
    int insertFolder(CollectionFolder record);

    /**
     * 查找该用户的拥有的文件夹
     * */
    @Select("select * from collectionFolder where user_id=#{user_id}")
    List<CollectionFolder> selectByUser(Integer user_id);

    /**
     * 给文件夹改名
     * */
    @Update("update collectionFolder set folderName=#{folderName} where user_id=#{user_id}")
    int updateByPrimaryKey(@Param("user_id") Integer user_id,@Param("folderName") String folderName);

    @Select("select * from collectionFolder where user_id=#{user_id}")
    List<CollectionFolderModel> selectByUserPage(Integer user_id, Page page);

}