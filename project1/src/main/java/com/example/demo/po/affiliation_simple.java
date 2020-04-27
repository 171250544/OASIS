package com.example.demo.po;

public class affiliation_simple {
    private int affiliationid;
    private String affiliationname;
    private int documentcount;
//ddd
    public affiliation_simple(String affiliationname, int documentcount) {
        this.affiliationname = affiliationname;
        this.documentcount = documentcount;
    }

    public affiliation_simple() {
    }

    public void setAffiliationid(int affiliationid) {
        this.affiliationid = affiliationid;
    }

    public void setAffiliationname(String affiliationname) {
        this.affiliationname = affiliationname;
    }

    public void setDocumentcount(int documentcount) {
        this.documentcount = documentcount;
    }

    public affiliation_simple(int affiliationid, String affiliationname, int documentcount) {
        this.affiliationid = affiliationid;
        this.affiliationname = affiliationname;
        this.documentcount = documentcount;
    }

    public int getAffiliationid() {
        return affiliationid;
    }

    public String getAffiliationname() {
        return affiliationname;
    }

    public int getDocumentcount() {
        return documentcount;
    }
}
