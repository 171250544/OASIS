package com.example.demo.po;

public class document_detail {
    public document_detail(int id, String title, String authorname, String authoraffiliations, String meeting,
                           int year, String abstracts, String doi, String link, String authorkey, String ieeeterms, int citationcount,
                           int referencecount, String publisher, String identifier) {
        this.id = id;
        this.title = title;
        this.authorname = authorname;
        this.authoraffiliations = authoraffiliations;
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

    public document_detail() {
    }

    private int id;
    private String title;
    private String authorname;
    private  String authoraffiliations;
    private String meeting;
    private int year;
    private String abstracts;
    private String doi;
    private  String link;
    private String authorkey;
    private String ieeeterms;
    private int citationcount;
    private  int referencecount;
    private String publisher;
    private  String identifier;

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

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthoraffiliations() {
        return authoraffiliations;
    }

    public void setAuthoraffiliations(String authoraffiliations) {
        this.authoraffiliations = authoraffiliations;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthorkey() {
        return authorkey;
    }

    public void setAuthorkey(String authorkey) {
        this.authorkey = authorkey;
    }

    public String getIeeeterms() {
        return ieeeterms;
    }

    public void setIeeeterms(String ieeeterms) {
        this.ieeeterms = ieeeterms;
    }

    public int getCitationcount() {
        return citationcount;
    }

    public void setCitationcount(int citationcount) {
        this.citationcount = citationcount;
    }

    public int getReferencecount() {
        return referencecount;
    }

    public void setReferencecount(int referencecount) {
        this.referencecount = referencecount;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


}
