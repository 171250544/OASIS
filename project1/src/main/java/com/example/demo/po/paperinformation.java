package com.example.demo.po;

public class paperinformation {
    private int document_id;
    private String author_name;
    private String author_affname;

    public paperinformation(int document_id, String author_name, String author_affname) {
        this.document_id = document_id;
        this.author_name = author_name;
        this.author_affname = author_affname;
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_affname() {
        return author_affname;
    }

    public void setAuthor_affname(String author_affname) {
        this.author_affname = author_affname;
    }
}
