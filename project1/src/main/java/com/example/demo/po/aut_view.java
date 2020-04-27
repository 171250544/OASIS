package com.example.demo.po;

public class aut_view {
    private String authorName;
    private String affName;
    private int paperCount;

    public aut_view(String authorName, String affName, int paperCount) {
        this.authorName = authorName;
        this.affName = affName;
        this.paperCount = paperCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAffName() {
        return affName;
    }

    public void setAffName(String affName) {
        this.affName = affName;
    }

    public int getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }
}
