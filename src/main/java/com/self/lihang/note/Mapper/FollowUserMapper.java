package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.FollowUser;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowUserMapper {

    @Insert("insert into followUser(fans_id,idol_id,follow_time) values(#{fans_id},#{idol_id},#{follow_time})")
    int addFollow(FollowUser followUser);

    @Delete("delete from followUser where fans_id = #{fans_id} and idol_id = #{idol_id}")
    int deleteFollow(@Param("fans_id")Integer fans_id, @Param("idol_id")Integer idol_id);

    @Select("select * from followUser where fans_id = #{fans_id}")
    List<FollowUser> findMyFollowPage(@Param("fans_id")Integer fans_id, @Param("page") Page page);

    @Select("select count(*) from followUser where idol_id = #{idol_id}")
    int countMyfans(@Param("idol_id")Integer idol_id);

    @Select("select count(*) from followUser where fans_id = #{fans_id}")
    int countMyidols(@Param("fans_id")Integer fans_id);

    @Select("select * from followUser where fans_id = #{fans_id} and idol_id = #{idol_id}")
    FollowUser isFollow(@Param("fans_id")Integer fans_id, @Param("idol_id")Integer idol_id);
}
