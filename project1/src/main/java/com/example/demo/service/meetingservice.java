package com.example.demo.service;

import com.example.demo.Vo.MeetingViewVo;
import com.example.demo.Vo.author_documentVo;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.*;
import com.example.demo.serviceinterface.dataserviceint;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.serviceinterface.meetingserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


@Service
public class meetingservice implements meetingserviceint {
    @Autowired
    documentserviceint doc;

    @Override
    public MeetingViewVo getMeetingViewVoByName(String name){
        List<author_documentVo> ads =doc.searchByList("","",name,"","","");
        List<String> terms = new ArrayList<String>();
        List<author_simple> author_simples = new ArrayList<author_simple>();

        //这里是将所有的论文作者取出，计算论文数。
        for (int i =0;i<ads.size();i++){
            author_documentVo au =ads.get(i);
            for (int j = 0;j<au.getAuthorIds().size();j++){
                int judge =0;//代表是否已经在结果中
                for (int k=0;k<author_simples.size();k++){
                    if (author_simples.get(k).getAuthorid()==au.getAuthorIds().get(j)){
                        author_simples.get(k).setDocumentcount(author_simples.get(k).getDocumentcount()+1);
                        judge=1;
                    }
                }
                if(judge==0){
                    author_simples.add(new author_simple(au.getAuthorIds().get(j),au.getAuthorNames().get(j),1));
                }
            }
        }
       // System.out.println(author_simples.get(0).getAuthorid());
        //下面对已经有的作者进行排序
        author_simples.sort(new Comparator<author_simple>() {
            @Override
            public int compare(author_simple o1, author_simple o2) {
                if (o1.getDocumentcount()<o2.getDocumentcount()){
                    return 1;
                }
                else if(o1.getDocumentcount()==o2.getDocumentcount()){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        //截取前五项
        if (author_simples.size()>=5){
            author_simples=author_simples.subList(0,5);
        }

        //下面是计算和他紧密相关的方向
        //得到所有方向
        List<String> allterms=new ArrayList<String>();
        Map<String,Integer> termsandcount = new HashMap<>();
        for (int i =0;i<ads.size();i++){
            int docid=ads.get(i).getId();
            allterms.add(doc.searchDetail(docid).getIeeeterms());
        }
        //为方向count
        for (String allterm:allterms) {
            String[] termsOfDoc =allterm.split(";");
            for (String term:termsOfDoc){
                if (!termsandcount.containsKey(term)){
                    termsandcount.put(term,1);
                }
                else {
                    termsandcount.put(term,termsandcount.get(term)+1);
                }
            }
        }
        //hash排序

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(termsandcount.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int p=0;
        for (Map.Entry<String,Integer> a:list){
            if (p<5) {
                terms.add(a.getKey());
                p++;
            }
            else {
                break;
            }
        }
        //System.out.println(terms);
        //System.out.println(ads);
        //System.out.println(author_simples);
        //System.out.println(name);
        //最后赋值
        MeetingViewVo meetingViewVo = new MeetingViewVo(name,ads,author_simples,terms);
        return meetingViewVo;
    }

    public boolean inAuthors(int a,List<author_simple> author_simples){

        return false;
    }
}

