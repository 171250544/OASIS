package com.example.demo.Controller;

import com.example.demo.Vo.AuthorViewVO;
import com.example.demo.Vo.MeetingViewVo;
import com.example.demo.Vo.authorActivenessRankingList;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.serviceinterface.dataserviceint;
import com.example.demo.serviceinterface.meetingserviceint;
import com.example.demo.serviceinterface.authorserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

import com.example.demo.po.papers_info;

/**
 * 2020 3 20 hj
 * 这个类用来处理和meeting有关的事务，如meeting的画像。
 */
@Controller
public class AuthorController {
    @Autowired
    private authorserviceint aut;

    @RequestMapping(value = "/getAuthorView",method = RequestMethod.POST)
    @ResponseBody
    public AuthorViewVO authorShow(@RequestBody Map<String,Object> params){
        return aut.getAuthorViewVoById(Integer.parseInt(params.get("authorId").toString()));

//        -------------------测试用代码--------------------------
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
//        System.out.println(Integer.parseInt(params.get("authorId").toString()));
//        ArrayList<papers_info> pap = new ArrayList<>();
//        papers_info papers = new papers_info(dd);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        pap.add(papers);
//        AuthorViewVO authorViewVO = new AuthorViewVO(
//                "Victor R. Basili",
//                37,
//                "University of Maryland College Park",
//                pap,
//                ieeeTerms,
//                98,
//                137,
//                963,
//                12,
//                ids,
//                names,
//                new int[]{1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0}
//        );
//        return authorViewVO;
//        ---------------------------------------------------------
    }

    @RequestMapping(value = "/getAllAuthorActiveness",method = RequestMethod.POST)
    @ResponseBody
    public authorActivenessRankingList getAllAuthorActiveness(){
        return aut.showAllList();

    }
    @RequestMapping(value = "/getAuthorActivenessByName",method = RequestMethod.POST)
    @ResponseBody
    public authorActivenessRankingList getAuthorActivenessByName(@RequestBody Map<String,Object> params){
        return aut.selectRankingListByName(params.get("name").toString());

    }


//    @RequestMapping("/getAuthorView")
//    @ResponseBody
//    public AuthorViewVO authorShow(){
//        return aut.getAuthorViewVoById(43990);
//
//    }
//    @RequestMapping( "/getAllAuthorActiveness")
//    @ResponseBody
//    public authorActivenessRankingList getAllAuthorActiveness(){
//        return aut.showAllList();
//
//    }
//    @RequestMapping("/getAuthorActivenessByName")
//    @ResponseBody
//    public authorActivenessRankingList getAuthorActivenessByName(){
//        return aut.selectRankingListByName("Z. Yang");
//
//    }
}
