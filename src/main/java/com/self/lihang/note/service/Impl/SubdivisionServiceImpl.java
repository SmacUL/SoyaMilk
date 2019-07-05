package com.self.lihang.note.service.Impl;


import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.Mapper.SubdivisionMapper;
import com.self.lihang.note.bean.Book;
import com.self.lihang.note.service.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SubdivisionServiceImpl implements SubdivisionService {

    @Autowired
    SubdivisionMapper subdivisionMapper;

    @Autowired
    BookMapper bookMapper;

//    public List<Book> findBookBySubdivisionId(Integer subdivisionId){
//        return bookMapper.selectBySubdivisionIdAndPage(subdivisionId);
//    }

}
