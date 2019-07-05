package com.self.lihang.note.service.Impl;


import com.self.lihang.note.Mapper.SubdivisionMapper;
import com.self.lihang.note.Mapper.TopCategoryMapper;
import com.self.lihang.note.bean.TopCategory;
import com.self.lihang.note.service.TopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TopCategoryServiceImpl implements TopCategoryService {

    @Autowired
    TopCategoryMapper topCategoryMapper;

    @Autowired
    SubdivisionMapper subdivisionMapper;

//    public boolean  addTopCategory(TopCategory topCategory){
//        if (topCategoryMapper.insert(topCategory) > 0){
//            return true;
//        }else return false;
//    }

    public List<TopCategory> selectAll(){
        List<TopCategory> topCategoryList = topCategoryMapper.selectAll();
        for (TopCategory topCategory:topCategoryList){
            topCategory.setSubdivisions(subdivisionMapper.selectByBelongTopId(topCategory.getCategoryId()));
        }
        return topCategoryList;
    }
}
