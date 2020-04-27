package com.example.demo.Vo;

import com.example.demo.po.au_aff;
import com.example.demo.po.document_detail;
import org.hibernate.validator.constraints.EAN;

import java.util.ArrayList;
import java.util.List;

/**
 * 将po的authorname authoraffiliations拆分成四个数组
 * 将po的ieeeterms改成数组。
 */
public class document_detailVo {

    private int id;
    private String title;
    private ArrayList<Integer> authorIds = new ArrayList<>();
    private ArrayList<String> authorNames =new ArrayList<>();
    private ArrayList<Integer> affIds=new ArrayList<>();
    private ArrayList<String> affNames=new ArrayList<>();
    private String meeting;
    private int year;
    private String abstracts;
    private String doi;
    private  String link;
    private String authorkey;
    private ArrayList<String> ieeeterms=new ArrayList<>();
    private int citationcount;
    private  int referencecount;
    private String publisher;
    private  String identifier;

    public  document_detailVo(document_detail document_detail, List<au_aff> au_affs){
        this.id=document_detail.getId();
        this.title=document_detail.getTitle();
        this.meeting=document_detail.getMeeting();
        this.year=document_detail.getYear();
        this.abstracts=document_detail.getAbstracts();
        this.doi=document_detail.getDoi();
        this.link=document_detail.getLink();
        this.authorkey=document_detail.getAuthorkey();
        this.citationcount=document_detail.getCitationcount();
        this.referencecount=document_detail.getReferencecount();
        this.publisher=document_detail.getPublisher();
        this.identifier=document_detail.getIdentifier();
        for (au_aff i:au_affs){
            this.authorIds.add(i.getAuthorId());
            this.authorNames.add(i.getAuthorName());
            this.affIds.add(i.getAffiliationId());
            this.affNames.add(i.getAffiliationName());
        }
        String[] ieeeterms=document_detail.getIeeeterms().split(";");
        for (String ieee:ieeeterms){
            this.ieeeterms.add(ieee);
        }
    }

    public document_detailVo() {
    }

    public document_detailVo(int id, String title, ArrayList<Integer> authorIds, ArrayList<String> authorNames, ArrayList<Integer> affIds, ArrayList<String> affNames, String meeting, int year, String abstracts, String doi, String link, String authorkey, ArrayList<String> ieeeterms, int citationcount, int referencecount, String publisher, String identifier) {
        this.id = id;
        this.title = title;
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.affIds = affIds;
        this.affNames = affNames;
        this.meeting = meeting;
        this.year = year;
        this.abstracts = abstracts;
        this.doi = doi;
        this.link = link;
        this.authorkey = authorkey;
        this.ieeeterms = ieeeterms;
        this.citationcount = citationcount;
        this.referencecount = referencecount;
        this.publisher = publisher;
        this.identifier = identifier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setAuthorkey(String authorkey) {
        this.authorkey = authorkey;
    }

    public void setIeeeterms(ArrayList<String> ieeeterms) {
        this.ieeeterms = ieeeterms;
    }

    public void setCitationcount(int citationcount) {
        this.citationcount = citationcount;
    }

    public void setReferencecount(int referencecount) {
        this.referencecount = referencecount;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getMeeting() {
        return meeting;
    }

    public int getYear() {
        return year;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public String getDoi() {
        return doi;
    }

    public String getLink() {
        return link;
    }

    public String getAuthorkey() {
        return authorkey;
    }

    public ArrayList<String> getIeeeterms() {
        return ieeeterms;
    }

    public int getCitationcount() {
        return citationcount;
    }

    public int getReferencecount() {
        return referencecount;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIdentifier() {
        return identifier;
    }
}
