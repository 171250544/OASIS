package com.example.demo.service;
import com.example.demo.Vo.Synonym;
import com.example.demo.Vo.TermViewVo;
import com.example.demo.Vo.author_documentVo;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.au_aff;
import com.example.demo.po.author_document;
import com.example.demo.po.author_simple;
import com.example.demo.po.document_detail;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.serviceinterface.documentserviceint;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class docunmentserviceTest {
    @Autowired
    docunmentservice doc ;
//    @Test
//    public void test() {
//        int a=558;
//        // assertEquals(5,a);
//        Assert.assertEquals(558,a);
//    }

    @Test
    public void search1() {
        int a=doc.search("Obfuscation").get(0).getId();
        // assertEquals(5,a);
        Assert.assertEquals(289,a);
    }

    @Test
    public void search2() {
        int a=doc.search("G. Saake&2016&Compositional Analyses").get(0).getId();
        assertEquals(1394,a);
    }
    @Test
    public void search3() {
        int a=doc.search("Nanjing&2018-2019").get(0).getId();
        assertEquals(228,a);
    }
//
    @Test
    public void searchbylist1() {
        int a=doc.searchbylist("X. Zhang","Nanjing Univ","2016 IEEE/ACM 38th International Conference on Software Engineering","","","2019").get(0).getId();
        assertEquals(590,a);
    }

    @Test
    public void searchNull() {
        List res=new ArrayList();
        List a=doc.search("asdasdasd");
        assertEquals(res,a);
    }

    @Test
    public void search_detail1() {
        String a=doc.searchDetail(5).getLink();
        assertEquals("https://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7965403",a);
    }
    @Test
    public void search_detail2(){
        String a=doc.searchDetail(3).getTitle();
        assertEquals("Crowdsourcing in Software Engineering: Models, Motivations, and Challenges",a);
    }

    @Test
    public void search_detailVo1() {
        List<Integer> a=doc.searchDetailVo(5).getAffIds();
        List<Integer> b=new ArrayList<>();
        b.add(809);
        b.add(831);
        assertEquals(b,a);
    }
    @Test
    public void countAff(){
        int a=doc.countByAff("Nanjing University");
        assertEquals(37,a);
    }

    @Test
    public void yearIncrease(){
        int a=doc.increaseOfThisYear("Nanjing University",2019);
        assertEquals(22,a);
    }

    @Test
    public void top5Key(){
        List<String> a=doc.searchTopNKeys("Nanjing University",2019);
        List<String> res= Arrays.asList("Symbolic Execution","Crowdsourced Testing","exception handling","JVM","Cloud computing");
        assertEquals(res,a);
    }

    @Test
    public void isyears(){
        assertEquals(true,doc.isyears("2018-2019"));
    }

    @Test
    public void isyears2(){
        assertEquals(false,doc.isyears("201g-2019"));
    }

    @Test
    public void searchall(){
        assertEquals(2731,doc.searchall().size());
    }

    @Test
    public void insertauthor_simpletest(){
        List<author_document> res=new ArrayList<>();
        author_simple a=new author_simple();
        res.add(new author_document(1,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        documentmapper mockmapper=Mockito.mock(documentmapper.class);
        docunmentservice mock=new docunmentservice(mockmapper);
        when(mockmapper.searchAll()).thenReturn(res);

        mock.searchauthor();
        verify(mockmapper,times(1)).insert_author_simple(anyObject());
    }
    @Test
    public void insertauthor_simpletest2(){
        List<author_document> res=new ArrayList<>();
        author_simple a=new author_simple();
        res.add(new author_document(1,"fsdf","fsdf","gdsgdf","gdsgvsd",10));

        res.add(new author_document(2,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        documentmapper mockmapper=Mockito.mock(documentmapper.class);
        docunmentservice mock=new docunmentservice(mockmapper);
        when(mockmapper.searchAll()).thenReturn(res);

        mock.searchauthor();
        verify(mockmapper,times(1)).insert_author_simple(anyObject());
    }

    @Test
    public void insertaffiliation_simpletest1(){
        List<author_document> res=new ArrayList<>();
        author_simple a=new author_simple();
        res.add(new author_document(1,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        res.add(new author_document(2,"fsdf","fsdf","gdsgdf","gdsgvsd",10));
        documentmapper mockmapper=Mockito.mock(documentmapper.class);
        docunmentservice mock=new docunmentservice(mockmapper);
        when(mockmapper.searchAll()).thenReturn(res);

        mock.searchaffiliation();
        verify(mockmapper,times(1)).insert_affiliation_simple(anyObject());
    }

    @Test
    public void getVOByAuthorDocTest(){
        List<author_documentVo> res=new ArrayList<>();
        List<author_document> in = new ArrayList<>();
        in.add(new author_document(1,"Yue","Li;Yue","Tang;Song","",0));
        in.add(new author_document(2,"r","Li","Tang","",0));
        documentmapper mockmapper=Mockito.mock(documentmapper.class);
        docunmentservice mock=new docunmentservice(mockmapper);

        List<au_aff> res1=new ArrayList<>();
        List<au_aff> res2=new ArrayList<>();
        au_aff Libai=new au_aff(1,1,"Li","Tang");
        au_aff Yur=new au_aff(2,2,"Yue","Song");
        res1.add(Libai);
        res1.add(Yur);
        res2.add(Libai);
        when(mockmapper.selectAuAffByDocId(1)).thenReturn(res1);
        when(mockmapper.selectAuAffByDocId(2)).thenReturn(res2);
        when(mockmapper.searchDetailById(1)).thenReturn(new document_detail(1,"","","","",2,"","","","","",20,30,"",""));
        when(mockmapper.searchDetailById(2)).thenReturn(new document_detail(2,"","","","",2,"","","","","",20,59,"",""));

        res=mock.getVoByAuthorDocument(in);
        System.out.println(res.get(0).getAuthorIds()+""+res.get(0).getAuthorNames()+""+res.get(0).getRef());
        Assert.assertEquals(2,res.get(0).getAuthorIds().size());
    }

    @Test
    public void getAuthorByDocIdsTest(){
       int[] a=new int[2];
       a[0]=1;
       a[1]=1551;
       String b="%git%";
        List<author_simple> author_simples=doc.document.getAuthorByIeeeTerm(b);
        Assert.assertEquals(1,author_simples.get(0).getDocumentcount());
    }
//    @Test
//    public void getVoByAuthorDocTest1(){
//        List<author_documentVo> author_documentVos=doc.searchByIeeeTerm("Learning");
//        Assert.assertEquals(57,author_documentVos.size());
//    }
}