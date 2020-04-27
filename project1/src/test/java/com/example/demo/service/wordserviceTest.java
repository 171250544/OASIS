package com.example.demo.service;

import com.example.demo.Vo.Synonym;
import com.example.demo.mapper.documentmapper;
import com.example.demo.mapper.wordmapper;
import com.example.demo.po.author_document;
import com.example.demo.po.author_simple;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

//import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class wordserviceTest {
    @Autowired
    wordservice word ;

    @Test
    public void  showSynonym_rootTest(){
        String keyword="111";
        wordmapper mockmapper=Mockito.mock(wordmapper.class);
        wordservice mock=new wordservice(mockmapper);
        mock.showSynonym_root(keyword);
        verify(mockmapper,times(1)).findSynByRoot(keyword);

    }
//    @Test
//    public void  showSynonymTest(){
//        String keyword="111";
//        wordmapper mockmapper=Mockito.mock(wordmapper.class);
//        wordservice mock=new wordservice(mockmapper);
//        mock.showSynonym(keyword);
//        verify(mockmapper,times(1)).findSynByUser(keyword);
//    }
    @Test
    public void  createSynonym_rootTest(){
        String keyword="wzy";
        ArrayList<String> list=new ArrayList<>();
        list.add("hj");
        list.add("xxc");

        Synonym syn=new Synonym(keyword,list);
        wordmapper mockmapper=Mockito.mock(wordmapper.class);
        wordservice mock=new wordservice(mockmapper);
        mock.createSynonym_root(syn);
        verify(mockmapper,times(2)).insert_similarwords_root(anyObject());
    }
//    @Test
//    public void  createSynonymTest(){
//        String keyword="wzy";
//        ArrayList<String> list=new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("e");
//        Synonym syn=new Synonym(keyword,list);
//        wordmapper mockmapper=Mockito.mock(wordmapper.class);
//        wordservice mock=new wordservice(mockmapper);
//        mock.createSynonym(syn);
//        verify(mockmapper,times(5)).insert_similarwords(anyObject());
//    }
    @Test
    public void  findSynonymTest(){
        String keyword="nanjing university jiangsu";
        ArrayList<String> item=new ArrayList<>();
        item.add("State Key Laboratory for Novel Software Technology, Department of Computer Science and Technology, Nanjing University, Nanjing, Jiangsu, P.R. China");
        item.add("State Key Laboratory for Novel Software Technology, Nanjing University, Jiangsu, China");

        assertEquals(item,word.findSynonym(keyword).getSynonymList());
    }

}