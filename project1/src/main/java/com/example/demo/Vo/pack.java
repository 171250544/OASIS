package com.example.demo.Vo;

import java.util.ArrayList;
import java.util.List;

public class pack {
    private int paperCount=0;
    private int increase=0;
    private List<String> keywords=new ArrayList<String>();

    public int getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(int paperCount) {
        this.paperCount = paperCount;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
