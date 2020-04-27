package com.example.demo.Vo;

import java.util.ArrayList;

public class Synonym {
    private String originWord;
    private ArrayList<String> synonymList;

    public Synonym() {
    }

    public Synonym(String originWord,ArrayList<String> synonymList) {
        this.originWord=originWord;
        this.synonymList = synonymList;
    }

    public String getOriginWord() {
        return originWord;
    }

    public void setOriginWord(String originWord) {
        this.originWord = originWord;
    }

    public ArrayList<String> getSynonymList() {
        return synonymList;
    }

    public void setSynonymList(ArrayList<String> synonymList) {
        this.synonymList = synonymList;
    }
}
