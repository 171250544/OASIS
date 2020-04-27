package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    @RequestMapping(value = "")
    public String getIndex() {
        return "main";
    }

    @RequestMapping(value = "main")
    public String getMain() {
        return "main";
    }

    @RequestMapping(value = "result")
    public String getResult(){return "result";}

    @RequestMapping(value = "detail")
    public String getDetail(){return "detail";}

    @RequestMapping(value = "interests")
    public String getInterests(){return "interests";}

    @RequestMapping(value = "manage_login")
    public String getM_login(){return "manage_login";}

    @RequestMapping(value = "manage_merge")
    public String getM_merge(){return "manage_merge";}

    @RequestMapping(value = "manage_upload")
    public String getM_upload(){return "manage_upload";}

    @RequestMapping(value = "ieeeTerms")
    public String getTerm(){return "ieeeTerms";}

    @RequestMapping(value = "meeting")
    public String getMeeting(){return "meeting";}

    @RequestMapping(value = "author")
    public String getAuthor(){return "author";}

    @RequestMapping(value = "affiliation")
    public String getAff(){return "affiliation";}

    @RequestMapping(value = "chart")
    public String getChart(){return "chart";}

}
