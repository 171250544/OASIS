package com.example.demo.Controller;

import com.example.demo.Vo.author_documentVo;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.po.author_document;
import com.example.demo.po.document_detail;
import com.example.demo.po.document_terms;
import com.example.demo.po.paperinformation;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.serviceinterface.wordserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Vo.pack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ContentOfDocumentController {
    @Autowired
    private documentserviceint doc;



    //按搜索的内容的返回
    @RequestMapping(value = "/string",method = RequestMethod.POST)
    @ResponseBody
    public List<author_document> selectbystring(@RequestBody Map<String,Object> params){
        String searchstring=params.get("stringforsearch").toString();
        System.out.println(searchstring);
        return doc.search(searchstring);
//        -----------以下为测试用代码---------------
//        List<author_document> a = new ArrayList<>();
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou3","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test We Need Some More And More and more and more and more","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity, shanghai keyansuo, wobuzhidaole","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        return a;
//        -------------------------------------
    }

    /**
     *
     * @param params
     * @return 相比于原来方法，返回数据类型改为author_documentVo，为了能实现存储到authorID，affiliationID
     * 2020 3 19 21:36 黄健
     */
    @RequestMapping(value = "/String",method = RequestMethod.POST)
    @ResponseBody
    public List<author_documentVo> selectByString(@RequestBody Map<String,Object> params){
        String searchstring=params.get("stringforsearch").toString();
        System.out.println(searchstring);
        return doc.searchByString(searchstring);
//        -----------以下为测试用代码---------------
//        List<author_documentVo> temp = new ArrayList<author_documentVo>();
//        ArrayList<Integer> a = new ArrayList<>();
//        a.add(2);
//        a.add(3);
//        ArrayList<String> b = new ArrayList<>();
//        b.add("f1 IWEN");
//        b.add("Li whyoud");
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        return temp;
//        -------------------------------------
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<author_document> selectbylist(@RequestBody Map<String,Object> params){
        System.out.println(params);
        String author=params.get("author").toString();
        String afflicatin=params.get("affiliations").toString();
        String meeting=params.get("meeting").toString();
        String keyword=params.get("keyWord").toString();
        String timebegin =params.get("timeBegin").toString();
        if(timebegin.length()>=4){
            timebegin=timebegin.substring(0,4);
        }
        String timeend=params.get("timeEnd").toString();
        if (timeend.length()>=4){
            timeend=timeend.substring(0,4);
        }
        //System.out.println(searchstring);
        return doc.searchbylist(author,afflicatin,meeting,keyword,timebegin,timeend);
        //        -----------以下为测试用代码---------------
//        List<author_document> a = new ArrayList<>();
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou3","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test We Need Some More And More and more and more and more","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity, shanghai keyansuo, wobuzhidaole","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        a.add(new author_document(17,"This is a title for Test","Wang Lin, Zhou Hongyu","Nanjing University, QinghuaUniversity","new publictitle",2019));
//        return a;
//        -------------------------------------
    }

    @RequestMapping("/det")
    @ResponseBody
    public document_detail selectDetail(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        System.out.println(id);
        return doc.searchDetail(id);
//        以下为测试用代码------------------------
//        document_detail dd = new document_detail(17,"Tom clancy's Ranbow Six Siege","Ubisoft Entertainment","France","Guillemot",1986,"bugs and bugs","Tom clancy's","Htttps://www.ubisoft.com.cn/","not now","games about",3,1835,"uplay","zhanwang");
//        return dd;
//        ----------------------------------
    }

    @RequestMapping("/Det")
    @ResponseBody
    public document_detailVo selectDetailVo(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        System.out.println(id);
        return doc.searchDetailVo(id);
//        以下为测试用代码------------------------
//        ArrayList<Integer> ids = new ArrayList<>();
//        ids.add(18);
//        ids.add(97);
//        ids.add(25);
//        ArrayList<String> names = new ArrayList<>();
//        names.add("Li Huangyun");
//        names.add("Feng Muqun");
//        names.add("Zhang He");
//        ArrayList<String> ieeeTerms = new ArrayList<>();
//        ieeeTerms.add("Software Engineering");
//        ieeeTerms.add("Computer Science");
//        ieeeTerms.add("Machine Learning");
//        document_detailVo dd = new document_detailVo(
//                17,
//                "Tom clancy's Ranbow Six Siege",
//                ids,
//                names,
//                ids,
//                names,
//                "AIFIII foiew683",
//                2019,
//                "sdfoqn oq q weo qwoei ori qwheto o isovov iroq erioq riv qo",
//                "sdoAOvv3wwOC-fe1-35",
//                "www.bilibili.com",
//                "wefoiew weoitq ptopvd",
//                ieeeTerms,
//                678,
//                36,
//                "Nanjing University",
//                "f38qwvq9eQVCEW"
//        );
//        return dd;
//        ----------------------------------
    }
    /**
     *
     * @param params
     * @return 相比于原来方法，返回数据类型改为author_documentVo，为了能实现存储到authorID，affiliationID
     */
    @RequestMapping(value = "/List",method = RequestMethod.POST)
    @ResponseBody
    public List<author_documentVo> selectByList(@RequestBody Map<String,Object> params){
        System.out.println(params);
        String author=params.get("author").toString();
        String afflicatin=params.get("affiliations").toString();
        String meeting=params.get("meeting").toString();
        String keyword=params.get("keyWord").toString();
        String timebegin =params.get("timeBegin").toString();
        if(timebegin.length()>=4){
            timebegin=timebegin.substring(0,4);
        }
        String timeend=params.get("timeEnd").toString();
        if (timeend.length()>=4){
            timeend=timeend.substring(0,4);
        }
        //System.out.println(searchstring);
        return doc.searchByList(author,afflicatin,meeting,keyword,timebegin,timeend);
        //        -----------以下为测试用代码---------------
//        List<author_documentVo> temp = new ArrayList<author_documentVo>();
//        ArrayList<Integer> a = new ArrayList<>();
//        a.add(2);
//        a.add(3);
//        ArrayList<String> b = new ArrayList<>();
//        b.add("f1 IWEN");
//        b.add("Li whyoud");
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        temp.add(new author_documentVo(7,"This is a title for Test",2019,679,a,b,a,b));
//        return temp;
//        -------------------------------------
    }



    @RequestMapping("/getInterests")
    @ResponseBody
    public pack habitsFound(){
        String searchstring="Nanjing University";
        int year=2019;
        int num=5;

        pack p=new pack();
        p.setPaperCount(doc.countByAff(searchstring));
        p.setIncrease(doc.increaseOfThisYear(searchstring,year));
        p.setKeywords(doc.searchTopNKeys(searchstring,num));

//        以下为测试用代码--------------------------------------
//        p.setPaperCount(37);
//        p.setIncrease(10);
//        List<String> temp = new ArrayList<>();
//        temp.add("软件工程");
//        temp.add("机器学习");
//        temp.add("人工智能");
//        temp.add("需求与商业模式");
//        temp.add("web开发");
//        p.setKeywords(temp);
//      ------------------------------------------

        return p;


    }


//    @RequestMapping("/all")
//    @ResponseBody
//    public  List<author_document> selectall(){
//        return doc.searchall();
//    }
    @RequestMapping("/pap")
    @ResponseBody
    public  List<paperinformation> selectpaperinformation(){
        return doc.searchpaperInformation();
    }
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
