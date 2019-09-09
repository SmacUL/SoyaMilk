package com.self.lihang.note.service.Impl;

import com.self.lihang.note.Mapper.BookMapper;
import com.self.lihang.note.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.text.Format;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    BookMapper bookMapper;

    @Test
    public void getBook() {
        Integer bookId  = 10;
        Book book = bookMapper.selectByPrimaryKey(bookId);
//        System.out.println(book.toString());

        DecimalFormat df = new DecimalFormat( "0.00 ");
        Double rate = book.getRate();
        book.setRate(Double.valueOf(df.format(book.getRate())));
        System.out.println(book.toString());
    }
}