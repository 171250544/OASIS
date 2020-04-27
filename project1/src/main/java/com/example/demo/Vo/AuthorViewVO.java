package com.example.demo.Vo;

import com.example.demo.po.author_friend;
import com.example.demo.po.papers_info;

import java.util.ArrayList;

public class AuthorViewVO {
    private String authorName;
    private int affId;
    private String affiliation;
    private ArrayList<papers_info> papers;
    private ArrayList<String> terms;
    private double huoyuedu;//暂时不计算
    private int articleCount;
    private int citationCount;
    private int meetingCount;
    ArrayList<Integer> friendsID;
    ArrayList<String> friendsName;
    private int[] docbyyears = new int[20];


    public AuthorViewVO(String authorName, int affId, String affiliation, ArrayList<papers_info> papers, ArrayList<String> terms, double huoyuedu, int articleCount, int citationCount, int meetingCount, ArrayList<Integer> friendsID, ArrayList<String> friendsName, int[] docbyyears) {
        this.authorName = authorName;
        this.affId = affId;
        this.affiliation = affiliation;
        this.papers = papers;
        this.terms = terms;
        this.huoyuedu = huoyuedu;
        this.articleCount = articleCount;
        this.citationCount = citationCount;
        this.meetingCount = meetingCount;
        this.friendsID = friendsID;
        this.friendsName = friendsName;
        this.docbyyears = docbyyears;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAffId() {
        return affId;
    }

    public void setAffId(int affId) {
        this.affId = affId;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
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

    public double getHuoyuedu() {
        return huoyuedu;
    }

    public void setHuoyuedu(double huoyuedu) {
        this.huoyuedu = huoyuedu;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getCitationCount() {
        return citationCount;
    }

    public void setCitationCount(int citationCount) {
        this.citationCount = citationCount;
    }

    public int getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(int meetingCount) {
        this.meetingCount = meetingCount;
    }

    public ArrayList<Integer> getFriendsID() {
        return friendsID;
    }

    public void setFriendsID(ArrayList<Integer> friendsID) {
        this.friendsID = friendsID;
    }

    public ArrayList<String> getFriendsName() {
        return friendsName;
    }

    public void setFriendsName(ArrayList<String> friendsName) {
        this.friendsName = friendsName;
    }

    public int[] getDocbyyears() {
        return docbyyears;
    }

    public void setDocbyyears(int[] docbyyears) {
        this.docbyyears = docbyyears;
    }
}
