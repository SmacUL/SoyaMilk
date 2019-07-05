package com.self.lihang.note.service;



import com.self.lihang.note.bean.TopCategory;

import java.util.List;

public interface TopCategoryService {

//    boolean addTopCategory(TopCategory topCategory);

    List<TopCategory> selectAll();
}
