package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.Vo.Synonym;
import com.example.demo.serviceinterface.wordserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WordController {
    @Autowired
    private wordserviceint wor;

    /**
     * 查找相似词表的函数，仅返回在同义词表中已被定义为与查找的关键词同义的词（不包含查找的词本身）
     * @param params    前端传的JSON格式对象，包含keyword为要查找的词
     * @return  返回一个对象，包含一个列表，列表内容为已经被定义为与被查找词同义的词
     */
    @RequestMapping("/getSynonym")
    @ResponseBody
    public Synonym showSynonyms_root(@RequestBody Map<String,String> params){//管理员查看后台同义词表
        String key=params.get("keyword");
        return wor.showSynonym_root(key);
//        -----测试用代码-------
//        System.out.println(key);
//        ArrayList<String> temp = new ArrayList<>();
//        temp.add("Nanjing University JiangSu China");
//        temp.add("NJ University");
//        temp.add("Nanjing University JiangsSu Nanjing");
//        Synonym synonym = new Synonym("Nanjing University", temp);
//        return synonym;
//        ------------------------
    }

//    可删
//    public Synonym showSynonyms(@RequestBody Map<String,String> params){//管理员查看用户同义词表
//        String key=params.get("keyword");
//        return wor.showSynonym(key);
//    }

    /**
     *  合并函数，将两个同义的词定义在同义词表中
     * @param syn   前端传入JSON格式对象，使用map解析，包含需要原单词originWord, 和被定义为同义的单词列表keywordList
     * @return  返回一个String， 内容为"success"或"failure"，如果失败则本次插入全部回滚
     */
    @RequestMapping("/merge")
    @ResponseBody
    public String makeSynonyms_root(@RequestBody String syn){
       // System.out.println(syn);
        JSONObject object = JSONObject.parseObject(syn);
        String originWord = object.getString("originWord");
        List<String> keywordList = JSON.parseArray(object.getJSONArray("keywordList").toJSONString(), String.class);
        //System.out.println(keywordList.get(0));
        ArrayList<String> keys=new ArrayList<>();
        for (String k:keywordList){
            keys.add(k);
        }
        Synonym testSyn=new Synonym(originWord,keys);
       // System.out.println(testSyn.getOriginWord()+" "+testSyn.getSynonymList());
        return wor.createSynonym_root(testSyn);
//        -------------测试用代码------------
//        System.out.println(originWord);
//        System.out.println(keywordList);
//        System.out.println(keywordList.toString());
//        return "success";
//        --------------------------------
    }

//    可删
//    public void makeSynonyms(Synonym syn){
//        wor.createSynonym(syn);
//    }

    /**
     * 查找相似词函数，返回所有与查找词相似的词
     * @param params 前端传入json格式对象，使用map解析，包含keyword为要查找的词
     * @return  返回一个对象，对象中包含原查找单词originWord和一个相似词列表synonymList
     */
    @RequestMapping("/searchSynonym")
    @ResponseBody
    public Synonym findSynonym(@RequestBody Map<String,String> params){
        String key=params.get("keyword");
        return wor.findSynonym(key);
//        ------------测试用代码--------------------
//        System.out.println(key);
//        ArrayList<String> temp = new ArrayList<>();
//        temp.add("Nanjing University China");
//        temp.add("NJU");
//        temp.add("Nanjing University JiangsSu Nanjing China Xixia");
//        Synonym synonym = new Synonym("Nanjing University", temp);
//        return synonym;
//        -----------------------------------
    }
}
