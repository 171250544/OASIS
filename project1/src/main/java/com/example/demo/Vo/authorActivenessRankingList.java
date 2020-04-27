package com.example.demo.Vo;

import java.util.ArrayList;

public class authorActivenessRankingList {
    private ArrayList<Integer> authorIds=new ArrayList<>();
    private ArrayList<String> authorNames = new ArrayList<>();
    private ArrayList<Double> authorActiveness =new ArrayList<>();

    public authorActivenessRankingList(ArrayList<Integer> authorIds, ArrayList<String> authorNames, ArrayList<Double> authorActiveness) {
        this.authorIds = authorIds;
        this.authorNames = authorNames;
        this.authorActiveness = authorActiveness;
    }

    public ArrayList<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(ArrayList<Integer> authorIds) {
        this.authorIds = authorIds;
    }

    public ArrayList<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(ArrayList<String> authorNames) {
        this.authorNames = authorNames;
    }

    public ArrayList<Double> getAuthorActiveness() {
        return authorActiveness;
    }

    public void setAuthorActiveness(ArrayList<Double> authorActiveness) {
        this.authorActiveness = authorActiveness;
    }
}
