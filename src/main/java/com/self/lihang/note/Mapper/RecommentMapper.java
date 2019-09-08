package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.Recomment;
import com.self.lihang.note.pageModel.RecommentAndUserModel;
import com.self.lihang.note.pageModel.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommentMapper {

    @Insert("insert into recomment(user_id,comment_id,content) values(#{user_id},#{comment_id},#{content})")
    int addRecomment(Recomment recomment);

    @Select("select * from recomment where comment_id = #{comment_id}")
    List<RecommentAndUserModel> selectRecommentByIdAndPage(@Param("comment_id") Integer comment_id, @Param("page") Page page);
}
