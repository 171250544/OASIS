package com.example.demo.Controller;

import com.example.demo.Vo.AffViewVO;
import com.example.demo.Vo.affActivenessRankingList;
import com.example.demo.Vo.authorActivenessRankingList;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.po.papers_info;
import com.example.demo.serviceinterface.affserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class AffController {
    @Autowired
    private affserviceint affservice;

    @RequestMapping(value = "/getAffView", method = RequestMethod.POST)
    @ResponseBody
    public AffViewVO authorshow(@RequestBody Map<String, Object> params) {
        return affservice.getAffrViewVoByID(Integer.parseInt(params.get("AffId").toString()));

//        --------------测试用代码---------------------------
//        System.out.println(Integer.parseInt(params.get("AffId").toString()));
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
//        ArrayList<papers_info> pap = new ArrayList<>();
//        papers_info papers = new papers_info(dd);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        ArrayList<String> synonym = new ArrayList<>();
//        synonym.add("lajichang fjwe pcCLoel");
//        synonym.add("feng weuoawq qpweot");
//        AffViewVO affViewVO = new AffViewVO(
//                "University of Maryland College Park",
//                synonym,
//                pap,
//                ieeeTerms,
//                ids,
//                names,
//                ids,
//                names,
//                6876,
//                new int[]{1,2,3,45,6,7,8,9,4,2,3,0,1,2,3,4,8,5,2,5}
//        );
//        return affViewVO;
//        ---------------------------------------------------
    }

//    @RequestMapping("/getAffView")
//    @ResponseBody
//    public AffViewVO affShow(){
//        return affservice.getAffrViewVoByID(1254);
//    }

    @RequestMapping(value = "/getAllAffActiveness",method = RequestMethod.POST)
    @ResponseBody
    public affActivenessRankingList getAllAffActiveness(){
        return affservice.showAllList();

    }
    @RequestMapping(value = "/getAffActivenessByName",method = RequestMethod.POST)
    @ResponseBody
    public affActivenessRankingList getAffActivenessByName(@RequestBody Map<String,Object> params){
        return affservice.selectRankingListByName(params.get("name").toString());

    }

//    @RequestMapping( "/getAllAffActiveness")
//    @ResponseBody
//    public affActivenessRankingList getAllAffActiveness(){
//        return affservice.showAllList();
//
//    }
//    @RequestMapping( "/getAffActivenessByName")
//    @ResponseBody
//    public affActivenessRankingList getAffActivenessByName(){
//        return affservice.selectRankingListByName("nanjing");
//
//    }
}
