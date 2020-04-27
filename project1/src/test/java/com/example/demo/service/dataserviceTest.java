package com.example.demo.service;

import com.example.demo.mapper.documentmapper;
import com.example.demo.po.author_document;
import com.example.demo.po.author_simple;
import com.example.demo.serviceinterface.documentserviceint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class dataserviceTest {
    @InjectMocks
    dataservice data;
    @Mock
    documentserviceint doc;
    @Mock
    documentmapper d;
    @Test
    public void isdatatest1(){
        String[] item={"","","","","","","","32"};
        assertEquals(false,data.isdata(item));
    }

    @Test
    public void isdatatest2(){
        String[] item={"1","2","3","","","","",""};
        assertEquals(true,data.isdata(item));
    }

    @Test
    public void inauthor_documenttest1(){
        String[] item={"fsdf","2","","","","","",""};
        List<author_document> res=new ArrayList<>();
        res.add(new author_document(1,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        when(d.searchAll()).thenReturn(res);

        assertEquals(true,data.inauthordocument(item));
    }

    @Test
    public void inauthor_documenttest2(){
        String[] item={"fsd","2","","","","","",""};
        List<author_document> res=new ArrayList<>();
        res.add(new author_document(1,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        when(d.searchAll()).thenReturn(res);

        assertEquals(false,data.inauthordocument(item));
    }

    @Test
    public void csvtodetailtest(){
        String[] item={"","2","","","","2","","","","2","","","","","","","","2","","","","","","","","2","","","","","",""};
        assertEquals(0,data.csvtodetail(item).getCitationcount());
    }

    @Test
    public void csvtoauthortest(){
        String[] item={"","2","","","","2","","","","2","","","","","","","","2","","","","","","","","2","","","","","",""};
        assertEquals(2,data.csvtoau_do(item).getYear());
    }

    @Test
    public void csvtotermstest(){
        String[] item={"","2","","","","2","","","","2","","","","","","","","2","","","","","","","","2","","","","","",""};
        assertEquals("",data.csvtoterms(item).getINSPECNonControlledTerms());
    }
}