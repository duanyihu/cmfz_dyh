package com.baizhi;

import com.baizhi.dao.AdminDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CmfzDyhApplication.class)
@RunWith(SpringRunner.class)
public class TestAdmin {
    @Autowired
    private AdminDAO adminDAO;


    //Admindao
    @Test
    public void test1(){


    }
}
