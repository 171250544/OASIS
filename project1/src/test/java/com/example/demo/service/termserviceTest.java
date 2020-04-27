package com.example.demo.service;

import com.example.demo.Vo.*;
import com.example.demo.mapper.documentmapper;
import com.example.demo.mapper.termmapper;
import com.example.demo.po.documentForTermActiceness;
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
public class termserviceTest {
    @InjectMocks
    termservice term;
    @Mock
    documentserviceint doc;
    @Mock
    wordserviceint word;
    @Mock
    termmapper termmapper;
    @Test
    public void getMeetingViewVoTest(){
        ArrayList<String> aunames=new ArrayList<String>();
        ArrayList<Integer> auids = new ArrayList<Integer>();
        ArrayList<String> afnames=new ArrayList<String>();
        ArrayList<Integer> afids = new ArrayList<Integer>();
        aunames.add("Liabi");
        aunames.add("dufu");
        aunames.add("baijuyi");
        auids.add(1);
        auids.add(2);
        auids.add(3);
        afnames.add("Tang");
        afnames.add("Tang");
        afnames.add("Song");
        afids.add(1);
        afids.add(1);
        afids.add(1);

        author_documentVo a1=new author_documentVo(1,"Hua",2019,200,auids,aunames,afids,afnames);
        author_documentVo a2=new author_documentVo(2,"Yue",2020,90,auids,aunames,afids,afnames);
        author_documentVo a3=new author_documentVo(3,"Xue",2018,190,auids,aunames,afids,afnames);
        List<author_documentVo> a=new ArrayList<author_documentVo>();
        a.add(a1);
        a.add(a2);
        a.add(a3);

        when(doc.searchByIeeeTerm("Shi")).thenReturn(a);

        document_detail document_detail1=new document_detail(1,"Hua","","","",0,"","","","","Liu;Yu;xi;Wang;Wei:yao",1,1,"","");
        document_detail document_detail2=new document_detail(2,"Yue","","","",0,"","","","","Yu",1,1,"","");
        document_detail document_detail3=new document_detail(3,"Xue","","","",0,"","","","","Yu;Xi",1,1,"","");

        when(doc.searchDetail(1)).thenReturn(document_detail1);
        when(doc.searchDetail(2)).thenReturn(document_detail2);
        when(doc.searchDetail(3)).thenReturn(document_detail3);

        when(word.showSynonym_root("Tang")).thenReturn(new Synonym("Tang",new ArrayList<String>()));
        ArrayList<String> p=new ArrayList<>();
        p.add("Tang");
        when(word.showSynonym_root("Song")).thenReturn(new Synonym("Song",new ArrayList<>()));
        TermViewVo termViewVo=term.getTermViewVoByName("Shi");
       // System.out.println(termViewVo.getAuVos().get(0).getAuthorNames());
        System.out.println(termViewVo.getAffNames().get(0));
        System.out.println(termViewVo.getAffNames().get(1));
        //System.out.println(termViewVo.getDocbyyears()[19]);
        Assert.assertEquals(termViewVo.getAuthorIds().size(),3);
    }

    @Test
    public void getActivenessTest(){
        termservice r=new termservice();
        Assert.assertEquals(r.getTermActiveness(2018,10,20),20.7,1);
    }

    @Test
    public void showAllListTest(){

        List<documentForTermActiceness> documentForTermActicenesses=new ArrayList<>();
        documentForTermActicenesses.add(new documentForTermActiceness(1,2020,10,10,"java;english;opp;jjj"));
        documentForTermActicenesses.add(new documentForTermActiceness(2,2018,8,12,"java;english;opp"));

        when(termmapper.getdocumentForTermActiceness("%%")).thenReturn(documentForTermActicenesses);

        termActivenessRankingList termActivenessRankingList=term.showAllList();
        Assert.assertEquals(termActivenessRankingList.getTermActiveness().size(),4);
    }

    @Test
    public void showListByNameTest(){

        List<documentForTermActiceness> documentForTermActicenesses=new ArrayList<>();
        documentForTermActicenesses.add(new documentForTermActiceness(1,2020,10,10,"java;english;opp;jjj"));
        documentForTermActicenesses.add(new documentForTermActiceness(2,2018,8,12,"java;english;opp"));

        when(termmapper.getdocumentForTermActiceness("%j%")).thenReturn(documentForTermActicenesses);

        termActivenessRankingList termActivenessRankingList=term.selectRankingListByName("j");
        Assert.assertEquals(termActivenessRankingList.getTermActiveness().size(),2);
    }
}