package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.CollectionFolder;
import com.self.lihang.note.pageModel.CollectionFolderModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionFolderMapper {


    /**
     * 删除文件夹
     * */
    @Delete("delete from collectionFolder where folderId=#{folderId}")
    int deleteByPrimaryKey(@Param("folderId") Integer folderId);

    /**
     * 添加文件夹
     * */
    @Insert("insert into collectionFolder(folderName,user_id) values(#{folderName},#{user_id}) ")
    int insertFolder(CollectionFolder record);

    /**
     * 查找该用户的拥有的文件夹
     * */
    @Select("select * from collectionFolder where user_id=#{user_id}")
    List<CollectionFolder> selectByUser(@Param("user_id") Integer user_id);

    /**
     * 给文件夹改名
     * */
    @Update("update collectionFolder set folderName=#{folderName} where user_id=#{user_id}")
    int updateByPrimaryKey(@Param("user_id") Integer user_id,@Param("folderName") String folderName);

    @Select("select * from collectionFolder where user_id=#{user_id}")
    List<CollectionFolderModel> selectByUserPage(@Param("user_id") Integer user_id, Page page);

    /**
     * 2019-09-10 SmacUL
     * 利用 folderId 来查询文件夹的名称
     * @param folderId
     * @return
     */
    @Select("select folderName from collectionFolder where folderId=#{folderId}")
    String selectNameById(Integer folderId);
}