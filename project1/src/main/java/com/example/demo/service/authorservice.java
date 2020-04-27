package com.example.demo.service;

import com.example.demo.Vo.AuthorViewVO;
import com.example.demo.Vo.authorActiveness;
import com.example.demo.Vo.authorActivenessRankingList;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.mapper.authormapper;
import com.example.demo.mapper.documentmapper;
import com.example.demo.po.*;
import com.example.demo.serviceinterface.authorserviceint;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class authorservice implements authorserviceint {
    @Autowired
    documentmapper document;
    @Autowired
    authormapper autmapper;
//
    public AuthorViewVO getAuthorViewVoById(int authorId){

        ArrayList<papers_info> paperList=new ArrayList<>();//作者文章
        aut_view av=document.selectInfoById(authorId);
        String authorname=av.getAuthorName();//作者名
        String affname=av.getAffName();//作者机构
        int affId=autmapper.getAffIdByAffName(affname);
        double huoyuedu=0;
        int[] docbyyears = new int[20];
        ArrayList<String> terms=new ArrayList<>();//作者研究方向
        ArrayList<Integer> friendsID=new ArrayList<>();
        ArrayList<String> friendsName=new ArrayList<>();
//Todo 同义词表？
        List<Integer> docIds=document.searchDocIDByAuthor(authorname,affname);//根据作者找出每个文章的id
        int articleCount=docIds.size();//作者发表文章数
        int citationCount=0;//作者文章被引用数
        ArrayList<String> meetings= new ArrayList<>();
        int meetingCount=0;//作者参加会议数
        for(int id:docIds){
            document_detail dd=document.searchDetailById(id);
            List<au_aff> aaList=document.selectAuAffByDocId(id);
            document_detailVo ddVo=new document_detailVo(dd,aaList);
            papers_info pi=new papers_info(ddVo);
            paperList.add(pi);
            if (ddVo.getYear()>2000&&ddVo.getYear()<=2020){
                docbyyears[ddVo.getYear()-2001]=docbyyears[ddVo.getYear()-2001]+1;
            }
            huoyuedu=huoyuedu+getTermActiveness(ddVo.getYear(),ddVo.getCitationcount(),ddVo.getReferencecount());
            citationCount=citationCount+ddVo.getCitationcount();
            if(!meetings.contains(ddVo.getMeeting())){
                meetings.add(ddVo.getMeeting());
                meetingCount++;
            }
            for(String term:ddVo.getIeeeterms()){
                if(!terms.contains(term)){
                    terms.add(term);
                }
            }
            for(int i=0;i<ddVo.getAuthorIds().size();i++){
                int autID=ddVo.getAuthorIds().get(i);
                String autName=ddVo.getAuthorNames().get(i);
                if(autID!=authorId){
                    author_friend af=new author_friend(autID,autName);
                    friendsID.add(autID);
                    friendsName.add(autName);
                }
            }

        }
        AuthorViewVO authorViewVO=new AuthorViewVO(authorname,affId,affname,paperList,terms,huoyuedu,articleCount,citationCount,meetingCount,friendsID,friendsName,docbyyears);
        return authorViewVO;




    }
    double getTermActiveness(int year,int citationcount,int referencecount){
        double res=(1-0.05*(2020-year))*(5+0.8*referencecount+0.2*citationcount);
        return res;
    }

    public authorActivenessRankingList showAllList(){
        List<documentForAuthorActiceness> documents=autmapper.getdocumentForAuthorActiceness("%%");
        ArrayList<authorActiveness> activenesses=new ArrayList<>();
        ArrayList<Integer> authorIds=new ArrayList<>();
        ArrayList<String> authorNames = new ArrayList<>();
        ArrayList<Double> authorActiveness =new ArrayList<>();

        for(documentForAuthorActiceness da:documents){
            boolean isContained=false;
            int id=da.getAuthorId();
            String autname=da.getAuthorName();
            double huoyuedu=getTermActiveness(da.getYear(),da.getCitationcount(),da.getReferencecount());
            authorActiveness aa=new authorActiveness(id,autname,huoyuedu);
            for(authorActiveness act:activenesses){
                if(aa.getAuthorId()==act.getAuthorId()){
                    act.setAuthorActivenes(act.getAuthorActivenes()+aa.getAuthorActivenes());
                    isContained=true;
                    break;
                }
            }
            if(!isContained){
                activenesses.add(aa);
            }
        }
        Collections.sort(activenesses, new Comparator<com.example.demo.Vo.authorActiveness>() {
            @Override
            public int compare(com.example.demo.Vo.authorActiveness o1, com.example.demo.Vo.authorActiveness o2) {
                if(o1.getAuthorActivenes()<o2.getAuthorActivenes()){
                    return 1;
                }else if(o1.getAuthorActivenes()>o2.getAuthorActivenes()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(authorActiveness tem :activenesses){
            if(authorIds.size()<=50){
                authorIds.add(tem.getAuthorId());
                authorNames.add(tem.getAuthorName());
                authorActiveness.add(tem.getAuthorActivenes());
            }
        }
        authorActivenessRankingList res=new authorActivenessRankingList(authorIds,authorNames,authorActiveness);
        return  res;
    }

    public authorActivenessRankingList selectRankingListByName(String name){
        List<documentForAuthorActiceness> documents=autmapper.getdocumentForAuthorActiceness("%"+name+"%");
        ArrayList<authorActiveness> activenesses=new ArrayList<>();
        ArrayList<Integer> authorIds=new ArrayList<>();
        ArrayList<String> authorNames = new ArrayList<>();
        ArrayList<Double> authorActiveness =new ArrayList<>();

        for(documentForAuthorActiceness da:documents){
            boolean isContained=false;
            int id=da.getAuthorId();
            String autname=da.getAuthorName();
            double huoyuedu=getTermActiveness(da.getYear(),da.getCitationcount(),da.getReferencecount());
            authorActiveness aa=new authorActiveness(id,autname,huoyuedu);
            for(authorActiveness act:activenesses){
                if(aa.getAuthorId()==act.getAuthorId()){
                    act.setAuthorActivenes(act.getAuthorActivenes()+aa.getAuthorActivenes());
                    isContained=true;
                    break;
                }
            }
            if(!isContained){
                activenesses.add(aa);
            }
        }
        Collections.sort(activenesses, new Comparator<com.example.demo.Vo.authorActiveness>() {
            @Override
            public int compare(com.example.demo.Vo.authorActiveness o1, com.example.demo.Vo.authorActiveness o2) {
                if(o1.getAuthorActivenes()<o2.getAuthorActivenes()){
                    return 1;
                }else if(o1.getAuthorActivenes()>o2.getAuthorActivenes()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(authorActiveness tem :activenesses){
            if(authorIds.size()<=50){
                authorIds.add(tem.getAuthorId());
                authorNames.add(tem.getAuthorName());
                authorActiveness.add(tem.getAuthorActivenes());
            }
        }
        authorActivenessRankingList res=new authorActivenessRankingList(authorIds,authorNames,authorActiveness);
        return  res;
    }

    public authorActivenessRankingList selectRankingListByIDWithoutLimit(int autid){
        List<documentForAuthorActiceness> documents=autmapper.getdocumentForAuthorActicenessById(autid);
        ArrayList<authorActiveness> activenesses=new ArrayList<>();
        ArrayList<Integer> authorIds=new ArrayList<>();
        ArrayList<String> authorNames = new ArrayList<>();
        ArrayList<Double> authorActiveness =new ArrayList<>();

        for(documentForAuthorActiceness da:documents){
            boolean isContained=false;
            int id=da.getAuthorId();
            String autname=da.getAuthorName();
            double huoyuedu=getTermActiveness(da.getYear(),da.getCitationcount(),da.getReferencecount());
            authorActiveness aa=new authorActiveness(id,autname,huoyuedu);
            for(authorActiveness act:activenesses){
                if(aa.getAuthorId()==act.getAuthorId()){
                    act.setAuthorActivenes(act.getAuthorActivenes()+aa.getAuthorActivenes());
                    isContained=true;
                    break;
                }
            }
            if(!isContained){
                activenesses.add(aa);
            }
        }
        Collections.sort(activenesses, new Comparator<com.example.demo.Vo.authorActiveness>() {
            @Override
            public int compare(com.example.demo.Vo.authorActiveness o1, com.example.demo.Vo.authorActiveness o2) {
                if(o1.getAuthorActivenes()<o2.getAuthorActivenes()){
                    return 1;
                }else if(o1.getAuthorActivenes()>o2.getAuthorActivenes()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        for(authorActiveness tem :activenesses){
            authorIds.add(tem.getAuthorId());
            authorNames.add(tem.getAuthorName());
            authorActiveness.add(tem.getAuthorActivenes());
        }
        authorActivenessRankingList res=new authorActivenessRankingList(authorIds,authorNames,authorActiveness);
        return  res;
    }
}
