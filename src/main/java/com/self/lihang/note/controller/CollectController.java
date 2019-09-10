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


}
