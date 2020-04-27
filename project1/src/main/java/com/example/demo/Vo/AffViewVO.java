package com.example.demo.Vo;

import com.example.demo.po.papers_info;

import java.util.ArrayList;

public class AffViewVO {
    private String affName;
    private ArrayList<String> synonym;
    private ArrayList<papers_info> papers;
    private ArrayList<String> terms;
    private ArrayList<Integer> authorsID;
    private ArrayList<String> authorsName;
    private ArrayList<Integer> friendAffID;
    private ArrayList<String> friendAffName;
    private double huoyuedu;
    private int[] docbyyears = new int[20];

    public AffViewVO(String affName, ArrayList<String> synonym, ArrayList<papers_info> papers, ArrayList<String> terms, ArrayList<Integer> authorsID, ArrayList<String> authorsName, ArrayList<Integer> friendAffID, ArrayList<String> friendAffName, double huoyuedu, int[] docbyyears) {
        this.affName = affName;
        this.synonym = synonym;
        this.papers = papers;
        this.terms = terms;
        this.authorsID = authorsID;
        this.authorsName = authorsName;
        this.friendAffID = friendAffID;
        this.friendAffName = friendAffName;
        this.huoyuedu = huoyuedu;
        this.docbyyears = docbyyears;
    }

    public String getAffName() {
        return affName;
    }

    public void setAffName(String affName) {
        this.affName = affName;
    }

    public ArrayList<String> getSynonym() {
        return synonym;
    }

    public void setSynonym(ArrayList<String> synonym) {
        this.synonym = synonym;
    }

    public ArrayList<papers_info> getPapers() {
        return papers;
    }

    public void setPapers(ArrayList<papers_info> papers) {
        this.papers = papers;
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }

    public ArrayList<Integer> getAuthorsID() {
        return authorsID;
    }

    public void setAuthorsID(ArrayList<Integer> authorsID) {
        this.authorsID = authorsID;
    }

    public ArrayList<String> getAuthorsName() {
        return authorsName;
    }

    public void setAuthorsName(ArrayList<String> authorsName) {
        this.authorsName = authorsName;
    }

    public ArrayList<Integer> getFriendAffID() {
        return friendAffID;
    }

    public void setFriendAffID(ArrayList<Integer> friendAffID) {
        this.friendAffID = friendAffID;
    }

    public ArrayList<String> getFriendAffName() {
        return friendAffName;
    }

    public void setFriendAffName(ArrayList<String> friendAffName) {
        this.friendAffName = friendAffName;
    }

    public double getHuoyuedu() {
        return huoyuedu;
    }

    public void setHuoyuedu(double huoyuedu) {
        this.huoyuedu = huoyuedu;
    }

    public int[] getDocbyyears() {
        return docbyyears;
    }

    public void setDocbyyears(int[] docbyyears) {
        this.docbyyears = docbyyears;
    }
}
