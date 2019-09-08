package com.self.lihang.note.Mapper;

import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 删除用户
     * */
    @Delete("delete from User where userId=#{userId}")
    int deleteByPrimaryKey(Integer userId);

    /**
     * 注册
     * */
    @Insert("insert into User (account, password) values (#{account},#{password})")
    int addUser(@Param("user")User record);

    /**
     *  查找用户
     * */
    @Select("select * from User where userId=#{userId}")
    UserModel selectByPrimaryKey(@Param("userId")Integer userId);

    @Select("select * from User where userId=#{userId}")
    UserModel selectUserModelByPrimaryKey(Integer userId);
    /**
     *  查看账号是否存在
     * */
    @Select("select * from User where account=#{account}")
    User SelectByAccount(@Param("account") String account);

    /**
     * 校队账号的密码
     * */
    @Select("select * from User where account=#{account} and password=#{password}")
    User SelectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 更改密码
     * */
    @Update("update User set password=#{password} where userId=#{userId}")
    int updatePasswordByPrimaryKey(@Param("userId")Integer userId, @Param("password") String password);

    /**
     * 更换头像
     * */
    @Update("update User set avatarUrl=#{avatarUrl} where userId=#{userId}")
    int updateAvatarByPrimaryKey(@Param("userId")Integer userId, @Param("avatarUrl") String avatarUrl);


    @Update("update User set selfStyle=#{selfStyle} where userId=#{userId}")
    int updateselfStyleByPrimaryKey(@Param("userId")Integer userId, @Param("avatarUrl") String selfStyle);

}