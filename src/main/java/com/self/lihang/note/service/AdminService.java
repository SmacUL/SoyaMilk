package com.self.lihang.note.service;


import com.self.lihang.note.bean.Book;
import com.self.lihang.note.bean.User;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
     boolean addBook(Book book,MultipartFile file);
//     Book addBookCover(MultipartFile file);
//     String storeBookCover(MultipartFile file);

}
