package com.self.lihang.note.service;


import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.pageModel.UserInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    boolean UserSignUp(User user);

    boolean VerifyAdmit(User user);

    String updateAvatar(MultipartFile file);

    UserModel loadUserInfo(Integer userId);

    Page loadFollowUser(Integer userId,Page page);

    boolean isFollow(Integer userId);

    boolean cancelFollowUser(Integer userId);

    boolean updateselfStyle(String selfStyle);

    boolean followUser(Integer userId);

    UserInfo getUserInfo(Integer userId);
}
