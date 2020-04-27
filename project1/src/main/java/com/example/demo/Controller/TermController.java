package com.example.demo.Controller;

import com.example.demo.Vo.*;
import com.example.demo.serviceinterface.meetingserviceint;
import com.example.demo.serviceinterface.termserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

//实际需要的引入
import java.util.ArrayList;
import java.util.List;
/**
 * 2020 3 21 hj
 * 这个类用来处理和term有关的事务，如term的画像。term即研究方向。
 */
@Controller
public class TermController {
    @Autowired
    private termserviceint term;

    @RequestMapping(value = "/getTermView",method = RequestMethod.POST)
    @ResponseBody
    public TermViewVo getTermView(@RequestBody Map<String,Object> params){
        return term.getTermViewVoByName(params.get("termName").toString());

//        --------------下面为测试用代码-----------------------
//        System.out.println(params.get("termName").toString());
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
//        TermViewVo viewVo = new TermViewVo("Software Engineering",temp,a,b,a,b,13,687,new int[]{1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,0,0});
//        return viewVo;
//      ------------------------------------------------------
    }

    @RequestMapping(value = "/getAllTermActiveness",method = RequestMethod.POST)
    @ResponseBody
    public termActivenessRankingList getAllTermActiveness(){
        return term.showAllList();
    }

    @RequestMapping(value = "/getTermActivenessByName",method = RequestMethod.POST)
    @ResponseBody
    public termActivenessRankingList getTermActivenessByName(@RequestBody Map<String,Object> params){
        return term.selectRankingListByName(params.get("name").toString());
    }

    @RequestMapping(value = "/getTermChart",method = RequestMethod.POST)
    @ResponseBody
    public termConnectionVo getTermChartByName(@RequestBody Map<String,Object> params){

        return term.getTermChartByName(params.get("uri").toString());
    }
}
