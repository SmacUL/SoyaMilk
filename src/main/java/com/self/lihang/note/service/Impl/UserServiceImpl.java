package com.self.lihang.note.service.Impl;


import com.self.lihang.note.Mapper.CommentBookMapper;
import com.self.lihang.note.Mapper.FollowUserMapper;
import com.self.lihang.note.Mapper.NoteBooksMapper;
import com.self.lihang.note.Mapper.UserMapper;
import com.self.lihang.note.bean.FollowUser;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.UserModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.pageModel.UserInfo;
import com.self.lihang.note.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.self.lihang.note.utils.MyFileUtil;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    HttpSession session;

    @Autowired
    FollowUserMapper followUserMapper;

    @Autowired
    NoteBooksMapper noteBooksMapper;

    @Autowired
    CommentBookMapper commentBookMapper;


    public boolean UserSignUp(User user){
        User u = userMapper.SelectByAccount(user.getAccount());
        if (u == null){
            userMapper.addUser(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 名字要改一下
     * */
    public boolean VerifyAdmit(User user){
        User u = userMapper.SelectByAccountAndPassword(user.getAccount(),user.getPassword());
        if (u != null){
            session.setAttribute("user",u);
            User user1 = (User) session.getAttribute("user");
            System.out.println(user1.getUserId());
            return true;
        }else {
            return false;
        }
    }

    public String updateAvatar(MultipartFile file){
        User user = (User) session.getAttribute("user");
        String[] fileName = file.getOriginalFilename().split("\\.");
        String suffix = fileName[fileName.length-1];
        String fileSaveName = user.getUserId()+"-"+ System.currentTimeMillis() +"."+suffix;

        try {
            File outDir =new File(MyFileUtil.getResourcesUrl()+"/static/avatar");
            if(!outDir.exists())
                outDir.mkdir();
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileSaveName = "/static/avatar/" +fileSaveName;
        userMapper.updateAvatarByPrimaryKey(user.getUserId(),fileSaveName);
        return fileSaveName;
    }

    public UserModel loadUserInfo(Integer userId){
        if (userId == 0){
            User u = (User)session.getAttribute("user");
            return userMapper.selectUserModelByPrimaryKey(u.getUserId());
        }else {
            return userMapper.selectUserModelByPrimaryKey(userId);
        }
    }

    public Page loadFollowUser(Integer userId,Page page){
        if (userId == 0){
            User u = (User)session.getAttribute("user");
            userId = u.getUserId();
        }
        List<FollowUser> followUsers = followUserMapper.findMyFollowPage(userId,page);
        List<UserModel> userModels = new ArrayList<>();

        for (FollowUser followUser:followUsers){
            UserModel userModel = userMapper.selectUserModelByPrimaryKey(followUser.getIdol_id());
            userModel.setFansNumber(followUserMapper.countMyfans(followUser.getIdol_id()));
            userModel.setNoteNumber(noteBooksMapper.countMynoteNumber(followUser.getIdol_id()));
            userModels.add(userModel);
        }
        page.setList(userModels);
        return page;
    }

    public boolean followUser(Integer userId){
        try {
            User u = (User)session.getAttribute("user");
            FollowUser followUser = new FollowUser();
            followUser.setFans_id(u.getUserId());
            followUser.setIdol_id(userId);
            followUser.setFollow_time(new Timestamp(new Date().getTime()));
            followUserMapper.addFollow(followUser);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isFollow(Integer userId){
        User u = (User) session.getAttribute("user");
        FollowUser followUser = followUserMapper.isFollow(u.getUserId(),userId);
        if (followUser == null){
            return false;
        }
        return true;
    }

    public boolean cancelFollowUser(Integer userId){
        try {
            User u = (User)session.getAttribute("user");
            followUserMapper.deleteFollow(u.getUserId(),userId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean updateselfStyle(String selfStyle){
        User u = (User) session.getAttribute("user");
        userMapper.updateselfStyleByPrimaryKey(u.getUserId(),selfStyle);
        return true;
    }

    public UserInfo getUserInfo(Integer userId){
        if (userId == 0){
            User user = (User) session.getAttribute("user");
            userId = user.getUserId();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setCommentNumber(commentBookMapper.selectUserCommentBookCount(userId));
        userInfo.setFollowed(followUserMapper.countMyidols(userId));
        userInfo.setFollower(followUserMapper.countMyfans(userId));
        userInfo.setNoteNumber(noteBooksMapper.countMynoteNumber(userId));
        return userInfo;
    }

}
