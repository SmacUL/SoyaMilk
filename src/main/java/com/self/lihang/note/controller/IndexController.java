package com.self.lihang.note.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/index")
    public String getIndex(){
        return "dj";
    }

    @RequestMapping("/")
    public String getIndex2(){
        return "dj";
    }
}
