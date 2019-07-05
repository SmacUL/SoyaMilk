package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.FollowUser;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FollowUserMapper {

    @Insert("insert into followUser(fans_id,idol_id,follow_time) values(#{fans_id},#{idol_id},#{follow_time})")
    int addFollow(FollowUser followUser);

    @Delete("delete from followUser where fans_id = #{fans_id} and idol_id = #{idol_id}")
    int deleteFollow(Integer fans_id,Integer idol_id);

    @Select("select * from followUser where fans_id = #{fans_id}")
    List<FollowUser> findMyFollowPage(Integer fans_id, Page page);

    @Select("select count(*) from followUser where idol_id = #{idol_id}")
    int countMyfans(Integer idol_id);

    @Select("select count(*) from followUser where fans_id = #{fans_id}")
    int countMyidols(Integer idol_id);

    @Select("select * from followUser where fans_id = #{fans_id} and idol_id = #{idol_id}")
    FollowUser isFollow(Integer fans_id,Integer idol_id);
}
