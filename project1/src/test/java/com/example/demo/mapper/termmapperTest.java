package com.example.demo.mapper;

import com.example.demo.Vo.termActivenessRankingList;
import com.example.demo.service.termservice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class termmapperTest {

//    @Autowired
//    termmapper termmapper;

    @Autowired
    termservice termservice;

//    @Test
//    void getdocumentForTermActiceness() {
//        Assert.assertEquals(355,termmapper.getdocumentForTermActiceness("%java%").size());
//    }

    @Test
    public void showAllListTest(){
//        termActivenessRankingList res=termservice.selectRankingListByName("java");
//        for (String a:res.getTermNames()){
//            System.out.println(a);
//        }
//        for (Double b:res.getTermActiveness()){
//            System.out.println(b);
//        }
//        Assert.assertEquals(20,res.getTermActiveness().size());
    }

}