package com.example.demo.service;

import com.example.demo.Vo.*;
import com.example.demo.mapper.authormapper;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.au_aff;
import com.example.demo.po.aut_view;
import com.example.demo.po.documentForAuthorActiceness;
import com.example.demo.po.document_detail;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.serviceinterface.wordserviceint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

//import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class authorserviceTest {
    @InjectMocks
    authorservice aut ;
    @Mock
    documentmapper doc;
    @Mock
    authormapper authormapper;


    @Test
    public void getAuthorViewVoByIdTest(){
        aut_view av=new aut_view("wzy","nju",2);
        when(doc.selectInfoById(542)).thenReturn(av);
        when(authormapper.getAffIdByAffName("nju")).thenReturn(1);
        ArrayList<Integer> docids=new ArrayList<>();
        docids.add(12);
        docids.add(15);
        when(doc.searchDocIDByAuthor("wzy","nju")).thenReturn(docids);
        document_detail dd1=new document_detail(12,"computer","wzy;xxc;hj","nju;nju;nju","",2020,"","","","","",2,5,"","");
        document_detail dd2=new document_detail(12,"games","wzy;cl;lwj","nju;nju;nju","",2020,"","","","","",2,5,"","");
        when(doc.searchDetailById(12)).thenReturn(dd1);
        when(doc.searchDetailById(15)).thenReturn(dd2);
        au_aff af0=new au_aff(541,211,"lwj","nju");
        au_aff af1=new au_aff(542,211,"wzy","nju");
        au_aff af2=new au_aff(543,211,"cl","nju");
        au_aff af3=new au_aff(544,211,"hj","nju");
        au_aff af4=new au_aff(551,211,"xxc","nju");
        List<au_aff> list1=new ArrayList<>();
        list1.add(af1);
        list1.add(af4);
        list1.add(af3);
        List<au_aff> list2=new ArrayList<>();
        list2.add(af1);
        list2.add(af2);
        list2.add(af0);
        when(doc.selectAuAffByDocId(12)).thenReturn(list1);
        when(doc.selectAuAffByDocId(15)).thenReturn(list2);
        AuthorViewVO authorViewVO=aut.getAuthorViewVoById(542);
        Assert.assertEquals(authorViewVO.getAffId(),1);

    }
    @Test
    public void showAllListTest(){
        documentForAuthorActiceness da1=new documentForAuthorActiceness(542,"wzy",2020,5,5);
        documentForAuthorActiceness da2=new documentForAuthorActiceness(541,"lwj",2020,10,10);
        List<documentForAuthorActiceness> list=new ArrayList<>();
        list.add(da1);
        list.add(da2);
        when(authormapper.getdocumentForAuthorActiceness("%%")).thenReturn(list);
        authorActivenessRankingList res=aut.showAllList();
        Assert.assertEquals(res.getAuthorIds().size(),2 );

    }
    @Test
    public void selectRankingListByNameTest(){
        documentForAuthorActiceness da1=new documentForAuthorActiceness(542,"wzy",2020,5,5);
        documentForAuthorActiceness da2=new documentForAuthorActiceness(541,"lwj",2020,10,10);
        List<documentForAuthorActiceness> list=new ArrayList<>();
        list.add(da1);
        list.add(da2);
        when(authormapper.getdocumentForAuthorActiceness("%a%")).thenReturn(list);
        authorActivenessRankingList res=aut.selectRankingListByName("a");
        Assert.assertEquals(res.getAuthorIds().size(),2 );

    }
}