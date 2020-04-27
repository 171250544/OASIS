package com.example.demo.po;

public class aff_view {
    private String title;
    private String meeting;
    private String ieeeterms;

    public aff_view(String title, String meeting, String ieeeterms) {
        this.title = title;
        this.meeting = meeting;
        this.ieeeterms = ieeeterms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getIeeeterms() {
        return ieeeterms;
    }

    public void setIeeeterms(String ieeeterms) {
        this.ieeeterms = ieeeterms;
    }
}
