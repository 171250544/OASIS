package com.example.demo.service;

import com.example.demo.Vo.Synonym;
import com.example.demo.Vo.author_documentVo;
import com.example.demo.Vo.document;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.*;

import java.util.LinkedHashMap;
import com.example.demo.serviceinterface.documentserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Service
public class docunmentservice implements documentserviceint {
    @Autowired
    documentmapper document;

    public docunmentservice(documentmapper document) {
        this.document = document;
    }

    //3.5 add search for 2016-2019
    @Override
    public List<author_document> search(String index){
        String[] allneedtofind=index.split("&");
        List<author_document> temp;
        List<List<author_document>> results=new ArrayList<>();
        System.out.println(allneedtofind[0]);
        for (int i =0;i<allneedtofind.length;i++){
            if(isyears(allneedtofind[i])){
                String[] numbers = allneedtofind[i].split("-");
                int a =Integer.parseInt(numbers[0]);
                int b=Integer.parseInt(numbers[1]);
                temp=document.searchasyears(a,b);
            }
            else {
                temp = document.search("%" + allneedtofind[i] + "%");
            }
//            temp=search("h zhang");//测试桩的方法

            if (temp!=null) {
                //System.out.println(temp.get(0).getId());
                results.add(temp);
            }
        }

        List<author_document> result =results.get(0);
        //System.out.println(result);
        //System.out.println(results.get(1));
        for (int i = 0; i < results.size(); i++) {
            result=andd(result,results.get(i));
        }
        return result;
    }

    @Override
    public List<author_documentVo> searchByString(String a){
        List<author_documentVo> res=new ArrayList<author_documentVo>();
        res=getVoByAuthorDocument(search(a));
        return res;
    }

    @Override
    public List<author_document> searchbylist(String author,String afflication,String meeting,String keyword,String timeBegin,String timeEnd){
        int begin;
        int end;
        if (timeBegin==""){
            begin=0;
        }
        else {
            begin=Integer.parseInt(timeBegin);
        }
        if (timeEnd==""){
            end=9999;
        }
        else {
            end=Integer.parseInt(timeEnd);
        }
        return document.searchbylist("%"+author+"%","%"+afflication+"%","%"+meeting+"%","%"+keyword+"%",begin,end);
    }

    @Override
    public List<author_documentVo> searchByList(String author, String afflication, String meeting, String keyword, String timeBegin, String timeEnd){
        List<author_documentVo> res=new ArrayList<author_documentVo>();
        res=getVoByAuthorDocument(searchbylist(author,afflication,meeting,keyword,timeBegin,timeEnd));
        return res;
    }

    public List<author_document> andd(List<author_document> a,List<author_document> b){
        List<author_document> result=new ArrayList<author_document>();
        for (int i =0;i<a.size();i++){
            for (int j = 0; j <b.size() ; j++) {
                if (a.get(i).getId()==b.get(j).getId()){
                    result.add(a.get(i));
                    break;
                }
            }
        }
        return result;
    }

    //用于判断是否是年份区间类型
    public boolean isyears(String a){
        boolean judge1=true;
        boolean judge2=true;
        if (a.length()==9) {
            for (int i = 0; i < 4; i++) {
                if (!Character.isDigit(a.charAt(i))) {
                    judge1 = false;
                }
                if (!Character.isDigit(a.charAt(i + 5))) {
                    judge2 = false;
                }
            }
            if (a.charAt(4) == '-' & judge1 & judge2) {
                return true;
            }
        }
        return false;
    }
    @Override
    public document_detail searchDetail(int id){
        document_detail dd=document.searchDetailById(id);
        System.out.println(dd.getAbstracts());
//        document_detail dd=searchDetailById(id);

        return dd;
    }


    public List<author_document> searchall(){
        List<author_document> res=document.searchAll();
        return res;
    }
    public List<String> searchaffiliation(){
        Map<String,Integer> affMap=new HashMap<>();
        List<author_document> all=document.searchAll();
        for(author_document a:all){
            String affiliation_total=a.getAuthoraffiliations();
            if (affiliation_total.startsWith("\"")&affiliation_total.endsWith("\"")){
                affiliation_total=affiliation_total.substring(1,affiliation_total.length()-1);
            }
            String[] temp2=affiliation_total.split(";");
            ArrayList<String> affHolder=new ArrayList<>();

            for(int i=0;i<temp2.length;i++){
                if(temp2[i].length()>1){
                    if(temp2[i].charAt(0)==' '){
                        temp2[i]=temp2[i].substring(1);
// Tsinghua University; Tsinghua University; Tsinghua University; Tsinghua University
// Tsinghua University;
                    }
                }
                if(!affMap.containsKey(temp2[i])){
                    affMap.put(temp2[i],1);//机构首次出现，计入数据库且论文数为1
                    affHolder.add(temp2[i]);
                }
                else{  // A B C   -----  A A A  ----
                    if(!affHolder.contains(temp2[i])){//如果当前论文没有出现这个机构
                        affHolder.add(temp2[i]);
                        int currentCount=affMap.get(temp2[i]);
                        currentCount++;
                        affMap.put(temp2[i],currentCount);
                    }

                }
            }
        }
        List<String> res=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:affMap.entrySet()){
            String str=entry.getKey()+"@"+entry.getValue();
            res.add(str);
        }
        Collections.sort(res);
        for(String row:res){
            String[] s=row.split("@");
            if(s.length==2){
                affiliation_simple as=new affiliation_simple(s[0],Integer.parseInt(s[1]));
                document.insert_affiliation_simple(as);
            }
        }
        return res;
    }

    public ArrayList<author_simple> searchauthor(){
        List<author_document> all=document.searchAll();
        ArrayList<author_simple> autRes=new ArrayList<>();
        ArrayList<affiliation_simple> affRes=new ArrayList<>();

        for(author_document a:all){
            String name_total=a.getAuthorname();
            String affiliation_total=a.getAuthoraffiliations();
            String[] temp=name_total.split(";");//数据中作者名字和作者机构是一一对应的
            String[] temp2=affiliation_total.split(";");
            for(int i=0;i<temp.length;i++){
                temp[i]=temp[i].trim();
                temp2[i]=temp2[i].trim();
                //--------------------------------------------------------插入作者表数据
                if(findSameName(temp[i],temp2[i],autRes)==-1){//找不到匹配作者
                    author_simple aus=new author_simple(temp[i],temp2[i],1);
                    autRes.add(aus);
                }else {
                    int index=findSameName(temp[i],temp2[i],autRes);
                    autRes.get(index).setDocumentcount(autRes.get(index).getDocumentcount()+1);//count++
                }
                //---------------------------------------------------------
            }
        }

        for(author_simple as:autRes){
                document.insert_author_simple(as);
        }
        return autRes;
    }
    int findSameName(String name,String affName,List<author_simple> list){
        int index=-1;
        for(int i=0;i<list.size();i++){
            if((list.get(i).getAuthorname().equals(name))&&(list.get(i).getAuthoraffiliation().equals(affName))){
                index=i;
                break;
            }
        }
        return index;
    }
    public List<paperinformation> searchpaperInformation(){
        List<author_document> all=searchall();
        ArrayList<paperinformation> pi=new ArrayList<>();

        for(author_document a:all){
            int id=a.getId();
            String name_total=a.getAuthorname();
            String affiliation_total=a.getAuthoraffiliations();
            String[] temp=name_total.split(";");//数据中作者名字和作者机构是一一对应的
            String[] temp2=affiliation_total.split(";");
            for(int i=0;i<temp.length;i++) {
                temp[i] = temp[i].trim();
                temp2[i] = temp2[i].trim();
                paperinformation paper=new paperinformation(id,temp[i],temp2[i]);
                pi.add(paper);
            }
        }
        for(paperinformation p:pi){
            document.insert_paperinformation(p);
        }
        return pi;
    }

//    public List<String> searchauthor(){
//        Map<String, ArrayList<String>> affMap = new HashMap<String, ArrayList<String>>();
//        Map<String,Integer> countMap=new HashMap<String, Integer>();
//        List<author_document> all=document.searchAll();
//        for(author_document a:all){
//            String name_total=a.getAuthorname();
//            String affiliation_total=a.getAuthoraffiliations();
//            String title=a.getTitle();
//            String[] temp=name_total.split(";");//数据中作者名字和作者机构是一一对应的
//            String[] temp2=affiliation_total.split(";");
//            for(int i=0;i<temp.length;i++){
//                if(!affMap.containsKey(temp[i])){//如果作者首次出现，将名字、机构、论文数（1）同时加入
//                    ArrayList<String> aff=new ArrayList<>();
//                    aff.add(temp2[i]);
//                    affMap.put(temp[i],aff);
//                    countMap.put(temp[i],1);
//                }else{//作者已经出现过，判断机构是否被收入
//                    int current_count=countMap.get(temp[i]);//论文数+1
//                    current_count++;
//                    countMap.put(temp[i],current_count);
//
//                    if(!affMap.get(temp[i]).contains(temp2[i])){//机构未被收入
//                        affMap.get(temp[i]).add(temp2[i]);
//                        Collections.sort(affMap.get(temp[i]));
//
//                    }
//                }
//            }
//        }
//        List<String> res=new ArrayList<>();
//        for(Map.Entry<String,ArrayList<String>> entry:affMap.entrySet()){
//            String str="";
//            for(String a:entry.getValue()){
//                str=str+a+";";
//            }
//
//            String result_str=entry.getKey()+"@"+str+"@"+countMap.get(entry.getKey());
//            res.add(result_str);
//        }
//        Collections.sort(res);
//        int count=res.size();
//        for(String row:res){
//            String[] s=row.split("@");
//            if(s.length==3){
//                author_simple as=new author_simple(s[0],s[1],Integer.parseInt(s[2]));
//                document.insert_author_simple(as);
//            }
//        }
//        return res;
//    }
    public int countByAff(String name){
        name="Nanjing University";
        name="%"+name+"%";
        return document.countByAffiliation(name);
    }
    public int increaseOfThisYear(String name,int year){
//        name="Nanjing University";
//        year=2019;
        name="%"+name+"%";
        int last_year=year-1;
        return (document.countByAffiliationByYear(name,year)-document.countByAffiliationByYear(name,last_year));
    }

    public List<String>  searchTopNKeys(String name,int num){
        name="Nanjing University";
        name="%"+name+"%";
        num=5;
        List<document_detail> info=document.searchAllKeys(name);

        Map<String,Integer> keyCount=new HashMap<>();

        for(document_detail dt:info){
            String[] allKeys=dt.getAuthorkey().split(";");
            for(int i=0;i<allKeys.length;i++){
                if(allKeys[i].length()>1){
                    if(allKeys[i].charAt(0)==' '){
                        allKeys[i]=allKeys[i].substring(1);
                    }
                }
                if(allKeys[i].length()!=0){
                    if(!keyCount.containsKey(allKeys[i])){
                        keyCount.put(allKeys[i],1);
                    }else{
                        int currentCount=keyCount.get(allKeys[i]);
                        currentCount++;
                        keyCount.put(allKeys[i],currentCount);
                    }
                }


            }

        }
//        HashMap<String, Integer> finalOut = new LinkedHashMap<>();
//        finalOut=keyCount.entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));

        Map<String,Integer> result = new LinkedHashMap<>();

        keyCount.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(num)
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        Collection<String> keyCollection=result.keySet();
        List<String> res=new ArrayList<>(keyCollection);
        return res;
    }

//    @Override
    public List<author_documentVo> getVoByAuthorDocument(List<author_document> aus){
        List<author_documentVo> res=new ArrayList<author_documentVo>();
        for (int i=0;i<aus.size();i++) {
            author_document au=aus.get(i);
            int docId=au.getId();
            List<au_aff> au_affs=document.selectAuAffByDocId(docId);
            int ref=document.getReferencecountbyid(docId);
            author_documentVo auVo=new author_documentVo(au,au_affs,ref);
            res.add(auVo);
        }
        return res;
    }

//    @Override
    public List<author_documentVo> searchByIeeeTerm(String ieeeterm){
        ieeeterm="%"+ieeeterm+"%";
        List<author_document> ads=document.searchbyieeeterm(ieeeterm);
        List<author_documentVo> adVos =getVoByAuthorDocument(ads);
        return adVos;
    }

    @Override
    public document_detailVo searchDetailVo(int id){
        document_detail document_detail=searchDetail(id);
        List<au_aff> au_affs=document.selectAuAffByDocId(id);
        return new document_detailVo(document_detail,au_affs);
    }

    @Override
    public List<author_simple> getAuthorByIeeeterms(String ieee){
        return document.getAuthorByIeeeTerm("%"+ieee+"%");
    }
}

//--------------------------search方法的桩---------------------------------------------------------------------
//
// List<author_document> search(String name){
//    ArrayList<author_document> res=new ArrayList<>();
//    res.add(new author_document(1,"Statistical Errors in Software Engineering Experiments: A Preliminary Literature Review","R. P. Reyes Ch.; O. Dieste; E. R. Fonseca C.; N. Juristo",
//            "Univ. Politec. de Madrid, Madrid, Spain; Univ. Politec. de Madrid, Madrid, Spain; NA; Univ. Politec. de Madrid, Madrid, Spain","2018 IEEE/ACM 40th International Conference on Software Engineering (ICSE)",2018));
//    return res;

//document_detail searchDetailById(int id){
//    document_detail res=new document_detail();
//    return res;
//}