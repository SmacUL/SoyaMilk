package com.self.lihang.note.controller;


import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.Subdivision;
import com.self.lihang.note.bean.TopCategory;
import com.self.lihang.note.service.SubdivisionService;
import com.self.lihang.note.service.TopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@RestController
@RequestMapping(value = "/api/index")
public class CategoryController {

    @Autowired
    TopCategoryService topCategoryService;

    @Autowired
    SubdivisionService subdivisionService;

    @RequestMapping(value = "/getAllTopCategory")
    public List<TopCategory> SelectAllTopCategoryAndSubdivision(){
        return topCategoryService.selectAll();
    }

}
