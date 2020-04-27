package com.example.demo.po;

public class document_terms {
    private int id;
    private String title;
    private  String authorkeywords;
    private String IEEEkeywords;
    private String INSPECControlledTerms;

    public document_terms() {
    }

    private String INSPECNonControlledTerms;


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorkeywords(String authorkeywords) {
        this.authorkeywords = authorkeywords;
    }

    public void setIEEEkeywords(String IEEEkeywords) {
        this.IEEEkeywords = IEEEkeywords;
    }


    public void setINSPECNonControlledTerms(String INSPECNonControlledTerms) {
        this.INSPECNonControlledTerms = INSPECNonControlledTerms;
    }

    public void setINSPECControlledTerms(String INSPECControlledTerms) {
        this.INSPECControlledTerms = INSPECControlledTerms;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorkeywords() {
        return authorkeywords;
    }

    public String getIEEEkeywords() {
        return IEEEkeywords;
    }


    public String getINSPECNonControlledTerms() {
        return INSPECNonControlledTerms;
    }

    public String getINSPECControlledTerms() {
        return INSPECControlledTerms;
    }

    public document_terms(int id, String title, String authorkeywords, String IEEEkeywords, String mesh_Terms, String INSPECControlledTerms, String INSPECNonControlledTerms) {
        this.id = id;
        this.title = title;
        this.authorkeywords = authorkeywords;
        this.IEEEkeywords = IEEEkeywords;
        this.INSPECControlledTerms = INSPECControlledTerms;
        this.INSPECNonControlledTerms = INSPECNonControlledTerms;
    }
}
