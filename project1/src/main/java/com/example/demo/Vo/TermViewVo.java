package com.example.demo.Vo;

import com.example.demo.po.affiliation_simple;
import com.example.demo.po.author_simple;

import java.util.ArrayList;
import java.util.List;

/**
 * name:方向名
 * auVos：方向下论文
 * authorIds：活跃的作者ID，一般在五个以内
 * authorNames：活跃的作者姓名
 * affIds:活跃的机构Id，一般在五个以内
 * affNames：活跃的机构名字
 * papercount：方向下论文数
 * ref：方向下论文总引用数
 * huoyuedu：方向活跃度，暂时没计算，设为0
 * docByYears：论文数按年展示
 */
public class TermViewVo {
    private String name;
    private List<author_documentVo> auVos;
    private ArrayList<Integer> authorIds = new ArrayList<>();
    private ArrayList<String> authorNames =new ArrayList<>();
    private ArrayList<Integer> affIds=new ArrayList<>();
    private ArrayList<String> affNames=new ArrayList<>();
    private int paperCount=0;
    private int ref=0;
    private double huoyuedu =0;
    private int[] docbyyears = new int[20];

    public TermViewVo() {
    }

    public TermViewVo(String name, List<author_documentVo> auVos, List<author_simple> author_simples, List<affiliation_simple> affiliation_simples,int[] docbyyears ){
        this.name=name;
        this.auVos=auVos;
        for(int i=0;i<author_simples.size();i++){
            author_simple author_simple=author_simples.get(i);
            // System.out.println(author_simple.getAuthorid());
            this.authorIds.add(author_simple.getAuthorid());
            this.authorNames.add(author_simple.getAuthorname());

        }
        for (int i=0;i<affiliation_simples.size();i++){
            affiliation_simple affiliation_simple=affiliation_simples.get(i);
            this.affIds.add(affiliation_simple.getAffiliationid());
            this.affNames.add(affiliation_simple.getAffiliationname());
        }
        this.paperCount=0;
        this.ref=0;
        for (int i=0;i<auVos.size();i++){
            this.paperCount=this.getPaperCount()+1;
            this.ref=this.getRef()+auVos.get(i).getRef();
        }
        this.docbyyears=docbyyears;
    }

    public TermViewVo(String name, List<author_documentVo> auVos, ArrayList<Integer> authorIds, ArrayList<String> authorNames, ArrayList<Integer> affIds, ArrayList<String> affNames, int paperCount, int ref, int[] docbyyears) {
        this.name = name;
        this.auVos = auVos;
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.affIds = affIds;
        this.affNames = affNames;
        this.paperCount = paperCount;
        this.ref = ref;
        this.docbyyears = docbyyears;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuVos(List<author_documentVo> auVos) {
        this.auVos = auVos;
    }

    public void setAuthorIds(ArrayList<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public void setAffIds(ArrayList<Integer> affIds) {
        this.affIds = affIds;
    }

    public void setAffNames(ArrayList<String> affNames) {
        this.affNames = affNames;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setDocbyyears(int[] docbyyears) {
        this.docbyyears = docbyyears;
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

    public ArrayList<Integer> getAffIds() {
        return affIds;
    }

    public ArrayList<String> getAffNames() {
        return affNames;
    }

    public int getPaperCount() {
        return paperCount;
    }

    public int getRef() {
        return ref;
    }

    public int[] getDocbyyears() {
        return docbyyears;
    }

    public double getHuoyuedu() {
        return huoyuedu;
    }

    public void setHuoyuedu(double huoyuedu) {
        this.huoyuedu = huoyuedu;
    }

}
