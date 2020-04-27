package com.example.demo.po;

public class documentForTermActiceness {
    private int id;
    private int year;
    private int citationcount;
    private int referencecount;
    private String ieeeterms;

    public documentForTermActiceness(int id, int year, int citationcount, int referencecount, String ieeeterms) {
        this.id = id;
        this.year = year;
        this.citationcount = citationcount;
        this.referencecount = referencecount;
        this.ieeeterms = ieeeterms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCitationcount() {
        return citationcount;
    }

    public void setCitationcount(int citationcount) {
        this.citationcount = citationcount;
    }

    public int getReferencecount() {
        return referencecount;
    }

    public void setReferencecount(int referencecount) {
        this.referencecount = referencecount;
    }

    public String getIeeeterms() {
        return ieeeterms;
    }

    public void setIeeeterms(String ieeeterms) {
        this.ieeeterms = ieeeterms;
    }
}
