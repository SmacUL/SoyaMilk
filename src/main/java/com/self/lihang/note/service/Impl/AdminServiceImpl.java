package com.self.lihang.note.service.Impl;


import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.User;
import com.self.lihang.note.service.AdminService;
import com.self.lihang.note.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    BookMapper bookMapper;

    @Transactional
    public boolean addBook(Book book,MultipartFile file){
        book.setBookCover(MyFileUtil.saveFile(file,"/src/assets/book"));
        bookMapper.addBook(book);
        return true;
    }


//    public Book addBookCover(MultipartFile file){
//        Book book = new Book();
//
//        String[] fileName = file.getOriginalFilename().split("\\.");
//        String suffix = fileName[fileName.length-1];
//
//        String fileSaveName = file.getName()+"-"+ System.currentTimeMillis() +"."+suffix;
//        try {
//            File outDir =new File(MyFileUtil.getResourcesUrl()+"/src/assets/book");
//            if(!outDir.exists())
//                outDir.mkdir();
//            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        book.setBookCover(fileSaveName);
//        book.setBookId(bookMapper.addBookCover(fileSaveName));
//        return book;
//    }
//
//
//    public String storeBookCover(MultipartFile file){
//        String[] fileName = file.getOriginalFilename().split("\\.");
//        String suffix = fileName[fileName.length-1];
//        String fileSaveName = file.getName()+"-"+ System.currentTimeMillis() +"."+suffix;
//        try {
//            File outDir =new File(MyFileUtil.getResourcesUrl()+"/static/book");
//            if(!outDir.exists())
//                outDir.mkdir();
//            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fileSaveName;
//    }


}
