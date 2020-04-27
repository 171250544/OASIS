package com.example.demo.Controller;

import com.example.demo.Vo.pack;
import com.example.demo.po.author_document;
import com.example.demo.po.document_detail;
import com.example.demo.serviceinterface.dataserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class DataController {
    @Autowired
    private dataserviceint doc;

    @RequestMapping(value = "/uploadData",method = RequestMethod.POST)
    @ResponseBody
    public String insertcsv(@RequestBody Map<String,Object> params){
//        return "success";
        return doc.insertcsv(params.get("url").toString());
    }
    //
//
//    @RequestMapping("/all")
//    @ResponseBody
//    public  List<author_document> selectall(){
//        return doc.searchall();
//    }
//
//    @RequestMapping("/aut")
//    @ResponseBody
//    public  List<String> selectaut(){
//        return doc.searchauthor();
//    }
//
//    @RequestMapping("/aff")
//    @ResponseBody
//    public  List<String> selectaff(){
//        return doc.searchaffiliation();
//    }
}
