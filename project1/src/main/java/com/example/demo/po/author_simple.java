package com.example.demo.po;

public class author_simple {
    private int authorid;
    private String authorname;
    private  String authoraffiliation;
    private int documentcount;

    public author_simple(String authorname, String authoraffiliation, int documentcount) {
        this.authorname = authorname;
        this.authoraffiliation = authoraffiliation;
        this.documentcount = documentcount;
    }

    public author_simple(int authorid, String authorname, int documentcount) {
        this.authorid = authorid;
        this.authorname = authorname;
        this.documentcount = documentcount;
    }

    public author_simple() {
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public void setAuthoraffiliation(String authoraffiliation) {
        this.authoraffiliation = authoraffiliation;
    }

    public void setDocumentcount(int documentcount) {
        this.documentcount = documentcount;
    }

    public int getAuthorid() {
        return authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public String getAuthoraffiliation() {
        return authoraffiliation;
    }

    public int getDocumentcount() {
        return documentcount;
    }

    public author_simple(int authorid, String authorname, String authoraffiliation, int documentcount) {
        this.authorid = authorid;
        this.authorname = authorname;
        this.authoraffiliation = authoraffiliation;
        this.documentcount = documentcount;
    }
}
