package com.example.demo.po;

public class au_aff {
    private int authorId;
    private int affiliationId;
    private String authorName;
    private String affiliationName;

    public au_aff(int authorId, int affiliationId, String authorName, String affiliationName) {
        this.authorId = authorId;
        this.affiliationId = affiliationId;
        this.authorName = authorName;
        this.affiliationName = affiliationName;
    }

    public au_aff(int authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public au_aff() {
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAffiliationId(int affiliationId) {
        this.affiliationId = affiliationId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getAffiliationId() {
        return affiliationId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAffiliationName() {
        return affiliationName;
    }
}
