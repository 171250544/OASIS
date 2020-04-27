package com.example.demo.Vo;

import com.example.demo.po.au_aff;
import com.example.demo.po.author_document;
import com.example.demo.po.author_simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用于统一的论文信息的第一层展示，一般会在搜索中展示
 */
public class author_documentVo {
    private int id;
    private String title;
    private int year;
    private int ref;
    //规定author和affiliation的ID，Name一一对应。
    private ArrayList<Integer> authorIds =new ArrayList<>();
    private ArrayList<String> authorNames = new ArrayList<>();
    private ArrayList<Integer> affiliationIds= new ArrayList<>();
    private ArrayList<String> affiliationNames = new ArrayList<>();

    public author_documentVo(author_document ad, List<au_aff> af,int ref) {
        this.id=ad.getId();
        this.title=ad.getTitle();
        this.year=ad.getYear();
        this.ref=ref;
        for (int i=0;i<af.size();i++){
            this.authorIds.add(af.get(i).getAuthorId());
            this.authorNames.add(af.get(i).getAuthorName());
            this.affiliationIds.add(af.get(i).getAffiliationId());
            this.affiliationNames.add(af.get(i).getAffiliationName());
        }
    }

    public author_documentVo() {
    }

    public author_documentVo(int id, String title, int year, int ref, ArrayList<Integer> authorIds, ArrayList<String> authorNames, ArrayList<Integer> affiliationIds, ArrayList<String> affiliationNames) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.ref = ref;
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.affiliationIds = affiliationIds;
        this.affiliationNames = affiliationNames;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setAuthorIds(ArrayList<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public void setAffiliationIds(ArrayList<Integer> affiliationIds) {
        this.affiliationIds = affiliationIds;
    }

    public void setAffiliationNames(ArrayList<String> affiliationNames) {
        this.affiliationNames = affiliationNames;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRef() {
        return ref;
    }

    public ArrayList<Integer> getAuthorIds() {
        return authorIds;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public ArrayList<Integer> getAffiliationIds() {
        return affiliationIds;
    }

    public ArrayList<String> getAffiliationNames() {
        return affiliationNames;
    }
}
