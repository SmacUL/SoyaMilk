package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.CollectMapper;
import com.self.lihang.note.Mapper.CollectionFolderMapper;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.pageModel.CollectionFolderModel;
import com.self.lihang.note.pageModel.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectServiceImplTest {

    @Autowired
    HttpSession session;
    @Autowired
    CollectionFolderMapper collectionFolderMapper;
    @Autowired
    CollectMapper collectMapper;

    @Test
    public void getMyCollectFolderByPage() {
//        User u = (User)session.getAttribute("user");
        Integer user_id = 1;
        Page page = new Page();
        List<CollectionFolderModel> collectionFolderModels = collectionFolderMapper.selectByUserPage(1, page);
        for (CollectionFolderModel collectionFolderModel:collectionFolderModels){
            collectionFolderModel.setNumber(collectMapper.selectCountByFolderId(collectionFolderModel.getFolderId()));
        }
    }

    @Test
    public void getCollectionFolderNameById() {
        System.out.println(collectionFolderMapper.selectNameById(5));
    }
}