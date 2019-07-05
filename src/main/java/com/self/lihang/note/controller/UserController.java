package com.self.lihang.note.controller;



import com.self.lihang.note.Mapper.UserMapper;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.pageModel.UserInfo;
import com.self.lihang.note.service.UserService;
import com.self.lihang.note.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;


@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 登录&注册
     * */
    @PostMapping("/signUp/verify")
    public boolean signUpVerify(@RequestBody User user){
        return userService.UserSignUp(user);
    }

    @PostMapping("/login/verify")
    public boolean loginVerify(@RequestBody User user){
        return userService.VerifyAdmit(user);
    }

    /**
     * 更新
     * */
    @RequestMapping("/changeAvatar")
    public String changeAvatar(HttpServletRequest request){
        MultipartFile file = MyFileUtil.getFile(request);
        return userService.updateAvatar(file);
    }
    @RequestMapping("/changeSelfStyle")
    public boolean changeSelfStyle(String selfStyle){
        System.out.println(selfStyle);
        return userService.updateselfStyle(selfStyle);
    }

    /**
     * 加载用户信息
     * */
    @RequestMapping("/loadUser/{userId}")
    public UserModel loadUser(@PathVariable Integer userId){
        return userService.loadUserInfo(userId);
    }

    /**
     * 加载我所有关注的人
     * */
    @RequestMapping("/loadMyFollow/{userId}")
    public Page loadFollow(@PathVariable Integer userId,@RequestBody Page page){
     return userService.loadFollowUser(userId,page);
    }
    /**
     * 加载用户成就
     * */
    @RequestMapping("/getUserInfo/{userId}")
    public UserInfo getUserInfo(@PathVariable Integer userId){
        return userService.getUserInfo(userId);
    }
    /**
     * 取消&关注&是否关注
     * */
    @RequestMapping("/unfollow/{userId}")
    public boolean unFollowUser(@PathVariable Integer userId){
        return userService.cancelFollowUser(userId);
    }
    @RequestMapping("/follow/{userId}")
    public boolean followUser(@PathVariable Integer userId){
        return userService.followUser(userId);
    }
    @RequestMapping("/isFollow/{userId}")
    public boolean isFollow(@PathVariable Integer userId){
        return userService.isFollow(userId);
    }

}
