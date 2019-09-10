package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.CollectMapper;
import com.self.lihang.note.Mapper.CollectionFolderMapper;
import com.self.lihang.note.bean.Collect;
import com.self.lihang.note.bean.CollectionFolder;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.CollectionFolderModel;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    HttpSession session;

    @Autowired
    CollectionFolderMapper collectionFolderMapper;

    @Autowired
    CollectMapper collectMapper;

    public List<CollectionFolder> getMyCollectionFolder(){
        User u = (User) session.getAttribute("user");
        return collectionFolderMapper.selectByUser(u.getUserId());
    }

    public boolean collectBooktoFolder(Collect collect){
        try {
            collect.setCollectionTime(new Timestamp(new Date().getTime()));
            collectMapper.insertCollect(collect);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean cancelCollect(Collect collect){
        try {
            collectMapper.deleteCollect(collect);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createCollectFolder(CollectionFolder collectionFolder){
        try{
            User u = (User) session.getAttribute("user");
            collectionFolder.setUser_id(u.getUserId());
            collectionFolderMapper.insertFolder(collectionFolder);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Page getMyCollectFolderByPage(Integer userId,Page page){
        if (userId == 0){
            User u = (User)session.getAttribute("user");
            userId = u.getUserId();
        }
        List<CollectionFolderModel> collectionFolderModels = collectionFolderMapper.selectByUserPage(userId,page);
        for (CollectionFolderModel collectionFolderModel:collectionFolderModels){
            collectionFolderModel.setNumber(collectMapper.selectCountByFolderId(collectionFolderModel.getFolderId()));
        }
        page.setList(collectionFolderModels);
        return page;
    }

    /**
     * 2019-09-10 SmacUL
     * 利用 folderId 来查询文件夹的名称
     * @param folderId
     * @return
     */
    @Override
    public String getCollectionFolderNameById(Integer folderId) {

        return collectionFolderMapper.selectNameById(folderId);
    }

    /**
     * 2019-09-10
     * 删除此收藏文件夹
     * @param folderId
     * @return
     */
    @Override
    public boolean deleteFolderById(Integer folderId) {
        if (collectionFolderMapper.deleteByPrimaryKey(folderId) == 1) {
            return true;
        }
        return false;
    }

}
