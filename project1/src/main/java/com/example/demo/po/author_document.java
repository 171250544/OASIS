package com.example.demo.po;

public class author_document {
    private int id;
    private String title;
    private String authorname;
    private  String authoraffiliations;
    private String publictitle;
    private int year;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public void setAuthoraffiliations(String authoraffiliations) {
        this.authoraffiliations = authoraffiliations;
    }

    public void setPublictitle(String publictitle) {
        this.publictitle = publictitle;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorname() {
        return authorname;
    }

    public String getAuthoraffiliations() {
        return authoraffiliations;
    }

    public String getPublictitle() {
        return publictitle;
    }

    public int getYear() {
        return year;
    }

    public author_document() {
    }

    public author_document(int id, String title, String authorname, String authoraffiliations, String publictitle, int year) {
        this.id = id;
        this.title = title;
        this.authorname = authorname;
        this.authoraffiliations = authoraffiliations;
        this.publictitle = publictitle;
        this.year = year;
    }

}
