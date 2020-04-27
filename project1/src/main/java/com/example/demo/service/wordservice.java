package com.example.demo.service;

import com.example.demo.Vo.Synonym;
import com.example.demo.mapper.documentmapper;
import com.example.demo.mapper.wordmapper;
import com.example.demo.po.similar_word;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.serviceinterface.wordserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class wordservice implements wordserviceint {
    @Autowired
    wordmapper word;

    public wordservice(wordmapper word) {
        this.word = word;
    }


    public Synonym showSynonym_root(String keyword) {//展示系统同义词表
        ArrayList<String> synonymList = new ArrayList<>();
        synonymList.addAll(word.findSynByRoot(keyword));
        Synonym syn = new Synonym(keyword, synonymList);

        return syn;
    }
//    public Synonym showSynonym(String keyword) {//展示系统同义词表
//        ArrayList<String> synonymList = new ArrayList<>();
//        synonymList.addAll(word.findSynByUser(keyword));
//        Synonym syn = new Synonym(keyword, synonymList);
//
//        return syn;
//    }

    public String createSynonym_root(Synonym syn) {

        String originword = syn.getOriginWord();
        ArrayList<String> words = syn.getSynonymList();
        try {
            for (int i = 0; i < words.size(); i++) {
                similar_word sw = new similar_word(originword, words.get(i));
                word.insert_similarwords_root(sw);
            }
//            word.clear_similarwords();//管理员审批后清空用户同义词表//现在不用用户表了
            return "success";
        }
        catch (Exception e){
            return "failure";
        }


    }

//    public void createSynonym(Synonym syn) {
//        String originword = syn.getOriginWord();
//        ArrayList<String> words = syn.getSynonymList();
//        for (int i = 0; i < words.size(); i++) {
//            similar_word sw = new similar_word(originword, words.get(i));
//            word.insert_similarwords(sw);
//        }
//    }

    public Synonym findSynonym(String keyword) {//根据用户输入，查找同义词

        Collection<String> keyCollection=countAff().keySet();
        List<String> res=new ArrayList<>(keyCollection);//----------管理员可维护的表，现在是未手动管理状态。需要看过结果后手动写成一个数组
        //下面是查找算法
        String[] keys=keyword.split(" ");
        ArrayList<String> realKey=new ArrayList<>();

        for(String cut:keys){
            if(!res.contains(cut))
                realKey.add("%"+cut+"%");
        }
        ArrayList<List<String>> holder=new ArrayList<>();
        for(String key:realKey){
            List<String> tmp=word.selectAff(key);
            holder.add(tmp);
        }
        List<String> jiaoji=holder.get(0);
        if(holder.size()>1) {
            for (int i = 1; i < holder.size(); i++) {
                jiaoji.retainAll(holder.get(i));
            }
        }

        String tempkeyWord=keyword;
        System.out.println(tempkeyWord);
        List<String> rootSyns=word.findSynByRoot(tempkeyWord);//取出系统表中已有同义词
        jiaoji.removeAll(rootSyns);//排除掉系统已有同义词
        ArrayList<String> synonyms=new ArrayList<>();
        synonyms.addAll(jiaoji);
        Synonym syn=new Synonym(keyword,synonyms);
        return syn;
    }

     Map<String, Integer> countAff(){//用于生成分词表
        List<String> affs=word.selectAllAff();
        HashMap<String,Integer> map=new HashMap<>();
        for(String aff:affs){
            if(aff.length()!=0){
                String[] res=aff.split("\\s|,|\\.");
                for(String cut:res){
                    if(!map.containsKey(cut)){
                        map.put(cut,1);
                    }else{
                        int currentCount=map.get(cut);
                        currentCount++;
                        map.put(cut,currentCount);
                    }
                }
            }

        }
        Map<String,Integer> result = new LinkedHashMap<>();
        //将hashmap排序 limit的数字代表取最多的前n个
        map.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).limit(20)
                .forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

}