package com.example.demo.service;

import com.example.demo.Vo.MeetingViewVo;
import com.example.demo.Vo.author_documentVo;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.author_document;
import com.example.demo.po.document_detail;
import com.example.demo.serviceinterface.documentserviceint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class meetingserviceTest {
    @InjectMocks
    meetingservice meeting;
    @Mock
    documentserviceint doc;
    @Mock
    documentmapper d;

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
        afnames.add("Tang");
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

        when(doc.searchByList("","","IEEE2","","",""))
                .thenReturn(a);

        document_detail document_detail1=new document_detail(1,"Hua","","","",0,"","","","","Liu;Yu;xi;Wang;Wei:yao",1,1,"","");
        document_detail document_detail2=new document_detail(2,"Yue","","","",0,"","","","","Yu",1,1,"","");
        document_detail document_detail3=new document_detail(3,"Xue","","","",0,"","","","","Yu;Xi",1,1,"","");

        when(doc.searchDetail(1)).thenReturn(document_detail1);
        when(doc.searchDetail(2)).thenReturn(document_detail2);
        when(doc.searchDetail(3)).thenReturn(document_detail3);

        MeetingViewVo meetingViewVo=meeting.getMeetingViewVoByName("IEEE2");
        //System.out.println(meetingViewVo.getPaperCount()+" "+meetingViewVo.getRef()+" "+meetingViewVo.getAuthorIds()+" "+meetingViewVo.getAuVos()+" "+meetingViewVo.getAuthorNames()+" "+meetingViewVo.getTerms());
        Assert.assertEquals("Yu",meetingViewVo.getTerms().get(0));

    }
}