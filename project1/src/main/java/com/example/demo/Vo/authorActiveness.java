package com.example.demo.Vo;

import java.util.ArrayList;

public class authorActiveness {
    private int authorId;
    private String authorName;
    private double authorActivenes;

    public authorActiveness(int authorId, String authorName, double authorActivenes) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorActivenes = authorActivenes;
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

    public double getAuthorActivenes() {
        return authorActivenes;
    }

    public void setAuthorActivenes(double authorActivenes) {
        this.authorActivenes = authorActivenes;
    }
}
