package com.example.demo.service;

import com.example.demo.mapper.documentmapper;
import com.example.demo.po.*;
import com.example.demo.serviceinterface.dataserviceint;
import com.example.demo.serviceinterface.documentserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@Service
public class dataservice implements dataserviceint {
    @Autowired
    documentmapper document;
    @Autowired
    documentserviceint doc;

    public dataservice(documentmapper document, documentserviceint doc) {
        this.document = document;
        this.doc = doc;
    }

    @Override
    public String insertcsv(String url){
        int count=0;
        int count1=0;
        try {
            System.out.println(1);
            BufferedReader reader = new BufferedReader(new FileReader(url));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
               // String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                String[] item = line.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1); //双引号内的逗号不分割  双引号外的逗号进行分割
               // String last = item[1];//这就是你要的数据了
                //去除引号
                for (int i=0;i<item.length;i++){
                    if (item[i].startsWith("\"")&item[i].endsWith("\"")){
                        item[i]=item[i].substring(1,item[i].length()-1);
                    }
                }
                //System.out.println("dakaichenggong");
                if(isdata(item)) {

                    if (!inauthordocument(item)) {

                        author_document a = csvtoau_do(item);
                        document_detail b = csvtodetail(item);
                        document_terms c = csvtoterms(item);
                        //System.out.println(a.getAuthorname());
                        //插入数据库
                        document.insert_author_document(a);
                        document.insert_document_detail(b);
                        document.insert_document_terms(c);
                       // System.out.println(a.getAuthorname());
                       // System.out.println(b.getAbstracts());
                       // System.out.println(c.getAuthorkeywords());
                    }
                    else {
                        count++;
                    }
                }
                else {
                    System.out.println("Not data");
                    count1++;
                }
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                //System.out.println(last);
            }
        } catch(DataIntegrityViolationException e){
            System.out.println("too long to insert");
        }catch (Exception e) {
            e.printStackTrace();
        }
       //doc.searchaffiliation();
        //doc.searchauthor();
        if(count==0&&count1==0){
            return "success";
        }
        else {
            return count+" failed because these have existed in mysql   "+count1+" failed because these are not data   ";
        }
    }

    public author_document csvtoau_do(String[] item){
        author_document res =new author_document();
        res.setAuthorname(item[1]);
        res.setAuthoraffiliations(item[2]);
        res.setTitle(item[0]);

        res.setYear(Integer.parseInt(item[5]));
        res.setPublictitle(item[3]);
        return res;
    }

    public document_detail csvtodetail(String[] item){
        document_detail res=new document_detail();
        res.setTitle(item[0]);
        res.setAuthorname(item[1]);
        res.setAuthoraffiliations(item[2]);
        res.setYear(Integer.parseInt(item[5]));
        res.setAbstracts(item[10]);
        res.setDoi(item[13]);
        res.setLink(item[15]);
        res.setAuthorkey(item[16]);
        res.setIeeeterms(item[17]);
        if (item[21].length()==0){
            res.setCitationcount(0);
        }
        else {
            res.setCitationcount(Integer.parseInt(item[21]));
        }
        if (item[22].length()==0){
            res.setReferencecount(0);
        }
        else {
            res.setReferencecount(Integer.parseInt(item[22]));
        }
        res.setMeeting(item[3]);
        res.setIdentifier(item[28]);
        res.setPublisher(item[27]);
//        List<author_document> aus=document.search("%"+res.getTitle()+"%");
//        if(aus.size()>0){
//            author_document au = aus.get(0);
//            res.setId(au.getId());
//        }
        return res;
    }

    public document_terms csvtoterms(String[] item){
        document_terms res =new document_terms();
        res.setTitle(item[0]);
        res.setAuthorkeywords(item[16]);
        res.setIEEEkeywords(item[17]);
        res.setINSPECControlledTerms(item[18]);
        res.setINSPECNonControlledTerms(item[19]);
        return  res;
    }

    public boolean isdata(String[] terms) {
       // System.out.println(terms[1].length());
        //System.out.println(terms[2].length());
        //System.out.println(terms[7].length());
        if (terms[1].length()==0||terms[2].length()==0||terms[7].length()!=0){
            return false;
        }
        return true;
    }

    public boolean inauthordocument(String[] terms){
        List<author_document> author_documents=document.searchAll();
        for (int i=0;i<author_documents.size();i++){
            if(author_documents.get(i).getTitle().equals(terms[0])){
                return true;
            }
        }
        return false;
    }
}

