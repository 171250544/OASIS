package com.example.demo.Vo;

import java.util.ArrayList;

public class termActivenessRankingList {
    private ArrayList<String> termNames = new ArrayList<>();
    private ArrayList<Double> termActiveness =new ArrayList<>();

    public termActivenessRankingList() {
    }

    public termActivenessRankingList(ArrayList<String> termNames, ArrayList<Double> termActiveness) {
        this.termNames = termNames;
        this.termActiveness = termActiveness;
    }

    public void setTermNames(ArrayList<String> termNames) {
        this.termNames = termNames;
    }

    public void setTermActiveness(ArrayList<Double> termActiveness) {
        this.termActiveness = termActiveness;
    }

    public ArrayList<String> getTermNames() {
        return termNames;
    }

    public ArrayList<Double> getTermActiveness() {
        return termActiveness;
    }
}
