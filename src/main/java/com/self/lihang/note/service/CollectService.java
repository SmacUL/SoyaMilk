package com.self.lihang.note.service;

import com.self.lihang.note.bean.Collect;
import com.self.lihang.note.bean.CollectionFolder;
import com.self.lihang.note.pageModel.Page;

import java.util.List;

public interface CollectService {

    List<CollectionFolder> getMyCollectionFolder();
    boolean collectBooktoFolder(Collect collect);
    boolean cancelCollect(Collect collect);
    boolean createCollectFolder(CollectionFolder collectionFolder);
    Page getMyCollectFolderByPage(Integer userId, Page page);
}
