package com.self.lihang.note.controller;

import com.self.lihang.note.bean.Collect;
import com.self.lihang.note.bean.CollectionFolder;
import com.self.lihang.note.pageModel.Page;
import com.self.lihang.note.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/collect")
public class CollectController {

    @Autowired
    CollectService collectService;

    @RequestMapping("/loadMyCollectFolder")
    public List<CollectionFolder> loadMyCollectFolder(){
        return collectService.getMyCollectionFolder();
    }

    @RequestMapping("/collectBook")
    public boolean collectBook(@RequestBody Collect collect){
        return collectService.collectBooktoFolder(collect);
    }

    @RequestMapping("/unCollectBook")
    public boolean unCollectBook(@RequestBody Collect collect){
        return collectService.cancelCollect(collect);
    }

    @RequestMapping("/createdFolder")
    public boolean createdFolder(@RequestBody CollectionFolder collectionFolder){
        return collectService.createCollectFolder(collectionFolder);
    }

    @RequestMapping("/getCollectionFolderPage/{userId}")
    public Page getCollectFolderPage(@PathVariable Integer userId, @RequestBody Page page){
        return collectService.getMyCollectFolderByPage(userId, page);
    }

    /**
     * 2019-09-10 SmacUL
     * 利用 folderId 来查询文件夹的名称
     * @param folderId
     * @return
     */
    @RequestMapping("/getCollectionFolderName/{folderId}")
    public String getCollectionFolderName(@PathVariable Integer folderId) {
        return collectService.getCollectionFolderNameById(folderId);
    }

    /**
     * 2019-09-10
     * 删除此收藏文件夹
     * @param folderId
     * @return
     */
    @RequestMapping("/deleteFolder/{folderId}")
    public boolean deleteFolder(@PathVariable Integer folderId) {
        return collectService.deleteFolderById(folderId);
    }

}
