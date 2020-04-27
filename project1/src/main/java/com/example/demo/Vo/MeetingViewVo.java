package com.example.demo.Vo;

import com.example.demo.po.au_aff;
import com.example.demo.po.author_simple;

import java.util.ArrayList;
import java.util.List;

/**
 * name:会议名
 * auVos：会议下论文
 * authorIds：活跃的作者ID，一般在五个以内
 * authorNames：活跃的作者姓名
 * papercount：会议下论文
 * ref：会议下论文引用数
 * terms：相关研究方向，一般五个以内
 */
public class MeetingViewVo {
    private String name;
    private List<author_documentVo> auVos;
    private ArrayList<Integer> authorIds = new ArrayList<>();
    private ArrayList<String> authorNames =new ArrayList<>();
    private int paperCount;
    private int ref;
    private List<String> terms;

    public MeetingViewVo(String name , List<author_documentVo> author_documentVos, List<author_simple> author_simples, List<String> terms){
        this.name=name;
        this.auVos=author_documentVos;
        for(int i=0;i<author_simples.size();i++){
            author_simple author_simple=author_simples.get(i);
           // System.out.println(author_simple.getAuthorid());
            this.authorIds.add(author_simple.getAuthorid());
            this.authorNames.add(author_simple.getAuthorname());
        }
        this.paperCount=0;
        this.ref=0;
        for (int i=0;i<author_documentVos.size();i++){
            this.paperCount=this.getPaperCount()+1;
            this.ref=this.getRef()+author_documentVos.get(i).getRef();
        }
        this.terms=terms;
    }

    public MeetingViewVo(String name, ArrayList<author_documentVo> auVos, ArrayList<Integer> authorIds, ArrayList<String> authorNames, int paperCount, int ref, ArrayList<String> terms) {
        this.name = name;
        this.auVos = auVos;
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.paperCount = paperCount;
        this.ref = ref;
        this.terms = terms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuVos(ArrayList<author_documentVo> auVos) {
        this.auVos = auVos;
    }

    public void setAuthorIds(ArrayList<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }

    public String getName() {
        return name;
    }

    public List<author_documentVo> getAuVos() {
        return auVos;
    }

    public ArrayList<Integer> getAuthorIds() {
        return authorIds;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public int getPaperCount() {
        return paperCount;
    }

    public int getRef() {
        return ref;
    }

    public List<String> getTerms() {
        return terms;
    }
}
