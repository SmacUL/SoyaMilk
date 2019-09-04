package com.self.lihang.note.controller;

import com.self.lihang.note.NoteApplication;
import com.self.lihang.note.bean.Book;
import com.self.lihang.note.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoteApplication.class)
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    BookService bookService;


//    @Before
//    public void before() {
//        //获取mockmvc对象实例
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void getBook() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/book/getBook/{bookId}", 3) // 2
//                        .param("bookId","3")        // 3
                )
                .andReturn();
        int status = mvcResult.getResponse().getStatus(); // 5
        String responseString = mvcResult.getResponse().getContentAsString(); // 6

        System.out.println("--------------------");
        System.out.println(responseString);
    }

    @Test
    public void increase_Note() {
    }

    @Test
    public void decrease_Note() {
    }

    @Test
    public void getSubdivisionBooks() {
    }

    @Test
    public void getUserFollowBook() {
    }

    @Test
    public void getBookInfo() {
    }

    @Test
    public void isfollowBook() {
    }

    @Test
    public void followBook() {
    }

    @Test
    public void unfollowBook() {
    }

    @Test
    public void getHotBook() {
    }
}