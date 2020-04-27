package com.example.demo.Vo;

public class affActiveness {
    private int affId;
    private String affName;
    private double affActivenes;

    public affActiveness(int affId, String affName, double affActivenes) {
        this.affId = affId;
        this.affName = affName;
        this.affActivenes = affActivenes;
    }

    public int getAffId() {
        return affId;
    }

    public void setAffId(int affId) {
        this.affId = affId;
    }

    public String getAffName() {
        return affName;
    }

    public void setAffName(String affName) {
        this.affName = affName;
    }

    public double getAffActivenes() {
        return affActivenes;
    }

    public void setAffActivenes(double affActivenes) {
        this.affActivenes = affActivenes;
    }
}
