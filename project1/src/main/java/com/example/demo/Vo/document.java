package com.example.demo.Vo;

public class document {
    private int id;
    private String title;
    private String authorname;
    private  String workspace;
    private String publictitle;
    private int year;
    private String abs;
    private String doi;
    private String href;
    private String importance;
    private String reference;
    private String publisher;
    private String dentifier;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public void setPublictitle(String publictitle) {
        this.publictitle = publictitle;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDentifier(String dentifier) {
        this.dentifier = dentifier;
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

    public String getWorkspace() {
        return workspace;
    }

    public String getPublictitle() {
        return publictitle;
    }

    public int getYear() {
        return year;
    }

    public String getAbs() {
        return abs;
    }

    public String getDoi() {
        return doi;
    }

    public String getHref() {
        return href;
    }

    public String getImportance() {
        return importance;
    }

    public String getReference() {
        return reference;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDentifier() {
        return dentifier;
    }

    public document(int id, String title, String authorname, String workspace, String publictitle, int year, String abs, String doi, String href, String importance, String reference, String publisher, String dentifier) {
        this.id = id;
        this.title = title;
        this.authorname = authorname;
        this.workspace = workspace;
        this.publictitle = publictitle;
        this.year = year;
        this.abs = abs;
        this.doi = doi;
        this.href = href;
        this.importance = importance;
        this.reference = reference;
        this.publisher = publisher;
        this.dentifier = dentifier;
    }

}
