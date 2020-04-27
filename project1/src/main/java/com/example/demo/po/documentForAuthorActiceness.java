package com.example.demo.po;

public class documentForAuthorActiceness {
    private int authorId;
    private String authorName;
    private int year;
    private int citationcount;
    private int referencecount;

    public documentForAuthorActiceness(int authorId, String authorName, int year, int citationcount, int referencecount) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.year = year;
        this.citationcount = citationcount;
        this.referencecount = referencecount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
}
