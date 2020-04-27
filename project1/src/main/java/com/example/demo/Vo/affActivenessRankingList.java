package com.example.demo.Vo;

import java.util.ArrayList;

public class affActivenessRankingList {
    private ArrayList<Integer> affIds=new ArrayList<>();
    private ArrayList<String> affNames = new ArrayList<>();
    private ArrayList<Double> affActiveness =new ArrayList<>();

    public affActivenessRankingList(ArrayList<Integer> affIds, ArrayList<String> affNames, ArrayList<Double> affActiveness) {
        this.affIds = affIds;
        this.affNames = affNames;
        this.affActiveness = affActiveness;
    }

    public ArrayList<Integer> getAffIds() {
        return affIds;
    }

    public void setAffIds(ArrayList<Integer> affIds) {
        this.affIds = affIds;
    }

    public ArrayList<String> getAffNames() {
        return affNames;
    }

    public void setAffNames(ArrayList<String> affNames) {
        this.affNames = affNames;
    }

    public ArrayList<Double> getAffActiveness() {
        return affActiveness;
    }

    public void setAffActiveness(ArrayList<Double> affActiveness) {
        this.affActiveness = affActiveness;
    }
}
