package com.example.demo.Controller;

import com.example.demo.Vo.MeetingViewVo;
import com.example.demo.serviceinterface.dataserviceint;
import com.example.demo.serviceinterface.meetingserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

import com.example.demo.Vo.author_documentVo;
import java.util.ArrayList;
import java.util.List;

/**
 * 2020 3 20 hj
 * 这个类用来处理和meeting有关的事务，如meeting的画像。
 */
@Controller
public class MeetingController {
    @Autowired
    private meetingserviceint meeting;

    @RequestMapping(value = "/getMeetingView",method = RequestMethod.POST)
    @ResponseBody
    public MeetingViewVo getMeetingView(@RequestBody Map<String,Object> params){
        return meeting.getMeetingViewVoByName(params.get("meetingName").toString());

//      -------------以下为试用代码
//        System.out.println(params.get("meetingName").toString());
//        ArrayList<author_documentVo> temp = new ArrayList<author_documentVo>();
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
//        ArrayList<String> terms = new ArrayList<>();
//        terms.add("software engineering");
//        terms.add("computer science");
//        terms.add("fsodiqe qoweinoqnv");
//        MeetingViewVo viewVo = new MeetingViewVo("AAAI 2700",temp,a,b,36,51,terms);
//        return viewVo;
//        ---------------------------------------------------
    }

}
