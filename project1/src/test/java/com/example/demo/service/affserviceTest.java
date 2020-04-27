package com.example.demo.service;

import com.example.demo.Vo.AffViewVO;
import com.example.demo.Vo.AuthorViewVO;
import com.example.demo.Vo.affActivenessRankingList;
import com.example.demo.Vo.authorActivenessRankingList;
import com.example.demo.mapper.affmapper;
import com.example.demo.mapper.documentmapper;
import com.example.demo.mapper.wordmapper;
import com.example.demo.po.*;
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
public class affserviceTest {
    @InjectMocks
    affservice affservice ;
    @Mock
    documentmapper doc;
    @Mock
    wordmapper wor;
    @Mock
    affmapper affmapper;
    @Mock
    authorservice aut;
    @Test
    public void getAffrViewVoByIDTest(){
        when(doc.getAffNameByid(5)).thenReturn("nju");
        List<String> syn=new ArrayList<>();
        when(wor.findSynByRoot("nju")).thenReturn(syn);
        author_simple as1=new author_simple(542,"wzy","nju",2);
        author_simple as2=new author_simple(544,"hj","nju",4);
        List<author_simple> list=new ArrayList<>();
        list.add(as1);
        list.add(as2);
       when(doc.getAuthorByAff("nju")).thenReturn(list);

       List<Integer> docid1=new ArrayList<>();
       List<Integer> docid2=new ArrayList<>();
        docid1.add(12);
        docid1.add(15);
        docid2.add(12);
        docid2.add(20);
        when(doc.searchDocIDByAuthor("wzy","nju")).thenReturn(docid1);
        when(doc.searchDocIDByAuthor("hj","nju")).thenReturn(docid2);


        document_detail dd1=new document_detail(12,"computer","wzy;hj","nju;nju","",2020,"","","","","",5,5,"","");
        document_detail dd2=new document_detail(15,"software","wzy;xxc","nju;nju","",2020,"","","","","",5,5,"","");
        document_detail dd3=new document_detail(20,"games","hj;lwj","nju;nju","",2020,"","","","","",5,5,"","");
        when(doc.searchDetailById(12)).thenReturn(dd1);
        when(doc.searchDetailById(15)).thenReturn(dd2);
        when(doc.searchDetailById(20)).thenReturn(dd3);

        au_aff af0=new au_aff(541,211,"lwj","nju");
        au_aff af1=new au_aff(542,211,"wzy","nju");
        au_aff af2=new au_aff(544,211,"hj","nju");
        au_aff af3=new au_aff(551,211,"xxc","nju");

        List<au_aff> list1=new ArrayList<>();
        list1.add(af1);
        list1.add(af2);

        List<au_aff> list2=new ArrayList<>();
        list2.add(af1);
        list2.add(af3);
        List<au_aff> list3=new ArrayList<>();
        list3.add(af0);
        list3.add(af2);
        when(doc.selectAuAffByDocId(12)).thenReturn(list1);
        when(doc.selectAuAffByDocId(15)).thenReturn(list2);
        when(doc.selectAuAffByDocId(20)).thenReturn(list3);
        AffViewVO av=affservice.getAffrViewVoByID(5);
        Assert.assertEquals(av.getAuthorsID().size(),2);

    }
    @Test
    public void showAllListeTest(){
        List<String> affiliations=new ArrayList<>();
        affiliations.add("nju");
        when(affmapper.getAffNameByPart("%%")).thenReturn(affiliations);
        when(affmapper.getAffIdByName("nju")).thenReturn(1);
        List<Integer> authorList=new ArrayList<>();
        authorList.add(542);
        authorList.add(544);
        ArrayList<Integer> authorIds1=new ArrayList<>();
        ArrayList<String> authorNames1 = new ArrayList<>();
        ArrayList<Double> authorActiveness1 =new ArrayList<>();
        authorIds1.add(542);
        authorNames1.add("wzy");
        authorActiveness1.add(5.5);
        authorActivenessRankingList aa1=new authorActivenessRankingList(authorIds1,authorNames1,authorActiveness1);

        ArrayList<Integer> authorIds2=new ArrayList<>();
        ArrayList<String> authorNames2 = new ArrayList<>();
        ArrayList<Double> authorActiveness2 =new ArrayList<>();
        authorIds1.add(544);
        authorNames1.add("hj");
        authorActiveness1.add(8.5);
        authorActivenessRankingList aa2=new authorActivenessRankingList(authorIds1,authorNames1,authorActiveness1);

        when(aut.selectRankingListByIDWithoutLimit(542)).thenReturn(aa1);
        when(aut.selectRankingListByIDWithoutLimit(544)).thenReturn(aa2);
        List<String> syns=new ArrayList<>();
        when(affmapper.getSyn("nju")).thenReturn(syns);
        affActivenessRankingList res=affservice.showAllList();
        Assert.assertEquals(res.getAffIds().size(),1);
    }
    @Test
    public void selectRankingListByNameTest(){
        List<String> affiliations=new ArrayList<>();
        affiliations.add("nju");
        when(affmapper.getAffNameByPart("%ju%")).thenReturn(affiliations);
        when(affmapper.getAffIdByName("nju")).thenReturn(1);
        List<Integer> authorList=new ArrayList<>();
        authorList.add(542);
        authorList.add(544);
        ArrayList<Integer> authorIds1=new ArrayList<>();
        ArrayList<String> authorNames1 = new ArrayList<>();
        ArrayList<Double> authorActiveness1 =new ArrayList<>();
        authorIds1.add(542);
        authorNames1.add("wzy");
        authorActiveness1.add(5.5);
        authorActivenessRankingList aa1=new authorActivenessRankingList(authorIds1,authorNames1,authorActiveness1);

        ArrayList<Integer> authorIds2=new ArrayList<>();
        ArrayList<String> authorNames2 = new ArrayList<>();
        ArrayList<Double> authorActiveness2 =new ArrayList<>();
        authorIds1.add(544);
        authorNames1.add("hj");
        authorActiveness1.add(8.5);
        authorActivenessRankingList aa2=new authorActivenessRankingList(authorIds1,authorNames1,authorActiveness1);

        when(aut.selectRankingListByIDWithoutLimit(542)).thenReturn(aa1);
        when(aut.selectRankingListByIDWithoutLimit(544)).thenReturn(aa2);
        List<String> syns=new ArrayList<>();
        when(affmapper.getSyn("nju")).thenReturn(syns);
        affActivenessRankingList res=affservice.selectRankingListByName("ju");
        Assert.assertEquals(res.getAffIds().size(),1);
    }
}