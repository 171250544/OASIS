package com.example.demo.service;

import com.example.demo.Vo.*;
import com.example.demo.mapper.affmapper;
import com.example.demo.mapper.documentmapper;
import com.example.demo.mapper.wordmapper;
import com.example.demo.po.*;
import com.example.demo.serviceinterface.affserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class affservice implements affserviceint {
    @Autowired
    authorservice authorservice;
    @Autowired
    documentmapper document;
    @Autowired
    wordmapper word;
    @Autowired
    affmapper affmapper;
    public AffViewVO getAffrViewVoByID(int aff){
        String affname=document.getAffNameByid(aff);
        ArrayList<String> synonym=new ArrayList<>();
        ArrayList<papers_info> papers=new ArrayList<>();;
        ArrayList<String> terms=new ArrayList<>();
        ArrayList<Integer> authorsID=new ArrayList<>();
        ArrayList<String> authorsName=new ArrayList<>();
        ArrayList<Integer> friendAffID=new ArrayList<>();
        ArrayList<String> friendAffName=new ArrayList<>();
        double huoyuedu=0;
        int[] docbyyears = new int[20];
        ArrayList<Integer> papersId=new ArrayList<>();

        List<String> syns=word.findSynByRoot(affname);
        synonym.addAll(syns);//记录所有同义词
        syns.add(affname);
        for(String affinfo:syns){//记录同义词后，讲本词加入同义词表，对列表中所有名称进行查询
            List<author_simple> aut=document.getAuthorByAff(affinfo);
            System.out.println(aut.get(0).getAuthorname());
            for(author_simple as:aut){
                if(!authorsID.contains(as.getAuthorid())){
                    authorsID.add(as.getAuthorid());
                    authorsName.add(as.getAuthorname());
                }
                List<Integer> docIds=document.searchDocIDByAuthor(as.getAuthorname(),as.getAuthoraffiliation());//根据作者找出每个文章的id
                for(int paperid:docIds){//对每一个作者将文章录入
                    if(!papersId.contains(paperid)){
                        papersId.add(paperid);
                    }
                }
            }
        }
        for(int id:papersId) {
            document_detail dd = document.searchDetailById(id);
            List<au_aff> aaList = document.selectAuAffByDocId(id);
            document_detailVo ddVo = new document_detailVo(dd, aaList);
            papers_info pi = new papers_info(ddVo);
            papers.add(pi);
            if (ddVo.getYear()>2000&&ddVo.getYear()<=2020){
                docbyyears[ddVo.getYear()-2001]=docbyyears[ddVo.getYear()-2001]+1;
            }
            huoyuedu=huoyuedu+getActiveness(ddVo.getYear(),ddVo.getCitationcount(),ddVo.getReferencecount());
            for(String term:ddVo.getIeeeterms()){
                if(!terms.contains(term)){
                    terms.add(term);
                }
            }
            for(int i=0;i<ddVo.getAffIds().size();i++){
                int affid=ddVo.getAffIds().get(i);
                String aff_name=ddVo.getAffNames().get(i);
                System.out.println(aff_name);
                if(!friendAffID.contains(affid)){//TODO 合作机构包括自己本身
                    friendAffID.add(affid);
                    friendAffName.add(aff_name);
                }
            }
        }
        Collections.sort(papers, new Comparator<papers_info>() {
            @Override
            public int compare(papers_info o1, papers_info o2) {
                if(o1.getRefCount()<o2.getRefCount()){
                    return 1;
                }else if(o1.getRefCount()>o2.getRefCount()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        return new AffViewVO(affname,synonym,papers,terms,authorsID,authorsName,friendAffID,friendAffName,huoyuedu,docbyyears);



    }
   double getActiveness(int year,int citationcount,int referencecount){
        double res=(1-0.05*(2020-year))*(5+0.8*referencecount+0.2*citationcount);
        return res;
    }
    public affActivenessRankingList showAllList(){
        List<String> affiliations=affmapper.getAffNameByPart("%%");
        ArrayList<affActiveness> activenesses=new ArrayList<>();
        ArrayList<Integer> affIds=new ArrayList<>();
        ArrayList<String> affNames = new ArrayList<>();
        ArrayList<Double> hyds =new ArrayList<>();
        for(String aff:affiliations){
            int affId=affmapper.getAffIdByName(aff);
            double HYD=0;
            List<Integer> authorList=affmapper.getAuthorsByAffname(aff);
            for(int authorID:authorList){
                authorActivenessRankingList aar=authorservice.selectRankingListByIDWithoutLimit(authorID);
                if(aar.getAuthorActiveness().size()==1){
                    double autHYD=aar.getAuthorActiveness().get(0);
                    HYD=HYD+autHYD;
                }
            }
            affActiveness aa=new affActiveness(affId,aff,HYD);
            activenesses.add(aa);
        }
        //处理同义词
        ArrayList<affActiveness> finalList=mergeSyn(activenesses);
        Collections.sort(finalList, new Comparator<com.example.demo.Vo.affActiveness>() {
            @Override
            public int compare(com.example.demo.Vo.affActiveness o1, com.example.demo.Vo.affActiveness o2) {
                if(o1.getAffActivenes()<o2.getAffActivenes()){
                    return 1;
                }else if(o1.getAffActivenes()>o2.getAffActivenes()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(affActiveness tem:finalList){
            if(affIds.size()<=50){
                affIds.add(tem.getAffId());
                affNames.add(tem.getAffName());
                hyds.add(tem.getAffActivenes());
            }
        }


        return new affActivenessRankingList(affIds,affNames,hyds);
    }
    public affActivenessRankingList selectRankingListByName(String name){
        List<String> affiliations=affmapper.getAffNameByPart("%"+name+"%");
        ArrayList<affActiveness> activenesses=new ArrayList<>();
        ArrayList<Integer> affIds=new ArrayList<>();
        ArrayList<String> affNames = new ArrayList<>();
        ArrayList<Double> hyds =new ArrayList<>();
        for(String aff:affiliations){
            int affId=affmapper.getAffIdByName(aff);
            double HYD=0;
            List<Integer> authorList=affmapper.getAuthorsByAffname(aff);
            for(int authorID:authorList){
                authorActivenessRankingList aar=authorservice.selectRankingListByIDWithoutLimit(authorID);
                if(aar.getAuthorActiveness().size()==1){
                    double autHYD=aar.getAuthorActiveness().get(0);
                    HYD=HYD+autHYD;
                }
            }
            affActiveness aa=new affActiveness(affId,aff,HYD);
            activenesses.add(aa);
        }
        //处理同义词
        ArrayList<affActiveness> finalList=mergeSyn(activenesses);
        Collections.sort(finalList, new Comparator<com.example.demo.Vo.affActiveness>() {
            @Override
            public int compare(com.example.demo.Vo.affActiveness o1, com.example.demo.Vo.affActiveness o2) {
                if(o1.getAffActivenes()<o2.getAffActivenes()){
                    return 1;
                }else if(o1.getAffActivenes()>o2.getAffActivenes()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(affActiveness tem:finalList){
            if(affIds.size()<=50){
                affIds.add(tem.getAffId());
                affNames.add(tem.getAffName());
                hyds.add(tem.getAffActivenes());
            }
        }


        return new affActivenessRankingList(affIds,affNames,hyds);
    }

    ArrayList<affActiveness> mergeSyn(ArrayList<affActiveness> affList){ //[A,B,C,Y]
        ArrayList<affActiveness> res=new ArrayList<>();
        for(affActiveness aa:affList){
            List<String> syns=affmapper.getSyn(aa.getAffName());// A:[X,Y]
            if(syns.size()>0){
               //todo 同义词合并的算法
                res.add(aa);
            }else{
                res.add(aa);
            }
        }
        return res;
    }
}

