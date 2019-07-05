package com.self.lihang.note.controller;



import com.alibaba.fastjson.JSONObject;
import com.self.lihang.note.bean.Book;
import com.self.lihang.note.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /**
     * 管理员端搁置了
     * */
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/addBook")
    public boolean addBook(@RequestParam("bookForm") String bookForm,@RequestParam("file") MultipartFile file){
        Book book = JSONObject.parseObject(bookForm,Book.class);
        System.out.println(JSONObject.toJSONString(book));
        return adminService.addBook(book,file);
    }

}
