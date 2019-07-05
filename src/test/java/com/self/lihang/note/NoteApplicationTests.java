package com.self.lihang.note;

import com.self.lihang.note.Mapper.TopCategoryMapper;
import com.self.lihang.note.utils.MyFileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteApplicationTests {

    @Autowired
    TopCategoryMapper topCategoryMapper;

    @Test
    public void contextLoads() {
        MyFileUtil.getResourcesUrl();
    }

}
