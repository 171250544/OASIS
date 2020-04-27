package com.example.demo.po;

import com.example.demo.Vo.document_detailVo;

import java.util.ArrayList;

public class papers_info {
    private int id;
    private String title;
    private int year;
    private ArrayList<Integer> authorIds;
    private ArrayList<String> authorNames;
    private ArrayList<Integer> affIds;
    private ArrayList<String> affNames;
    private int refCount;

    public papers_info(document_detailVo dd) {
        this.id=dd.getId();
        this.title=dd.getTitle();
        this.year=dd.getYear();
        this.authorIds=dd.getAuthorIds();
        this.authorNames=dd.getAuthorNames();
        this.affIds=dd.getAffIds();
        this.affNames=dd.getAffNames();
        this.refCount=dd.getReferencecount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(ArrayList<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public ArrayList<Integer> getAffIds() {
        return affIds;
    }

    public void setAffIds(ArrayList<Integer> affIds) {
        this.affIds = affIds;
    }

    public ArrayList<String> getAffNames() {
        return affNames;
    }

    public void setAffNames(ArrayList<String> affNames) {
        this.affNames = affNames;
    }

    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }
}
