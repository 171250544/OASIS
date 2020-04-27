package com.example.demo.service;

import com.example.demo.Vo.*;
import com.example.demo.mapper.termmapper;
import com.example.demo.po.affiliation_simple;
import com.example.demo.po.author_simple;
import com.example.demo.po.documentForTermActiceness;
import com.example.demo.serviceinterface.documentserviceint;
import com.example.demo.serviceinterface.meetingserviceint;
import com.example.demo.serviceinterface.termserviceint;
import com.example.demo.serviceinterface.wordserviceint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.termmapper;
import java.util.*;
import java.math.*;


@Service
public class termservice implements termserviceint {
    @Autowired
    documentserviceint doc;

    @Autowired
    wordserviceint word;

    @Autowired
    termmapper termmapper;

    @Override
    public TermViewVo getTermViewVoByName(String name){
        //TermViewVo res=new TermViewVo();
        long starttime1 = System.currentTimeMillis();
        List<author_documentVo> adVos=doc.searchByIeeeTerm(name);
        List<author_simple> author_simples=new ArrayList<>();
        List<affiliation_simple> affiliation_simples=new ArrayList<>();
        long endtime1 = System.currentTimeMillis();
        System.out.println("find time cost " +(endtime1-starttime1) +" ms");
        int[] docbyyears=new int[20];
        //下面是根据已有的adVos来计算作者和机构在这一方向的论文数，并以此初步排序。

        long starttime2 = System.currentTimeMillis();
        double huoyuedu=0;

        //author_simples=doc.getAuthorByIeeeterms(name)
        for (int i =0;i<adVos.size();i++){
            //说明目前是第i条记录
            author_documentVo au =adVos.get(i);
            for (int j = 0;j<au.getAuthorIds().size();j++){
                //说明目前是第j个作者和机构
                int authorjudge =0;//代表作者是否已经在结果中
                for (int k=0;k<author_simples.size();k++){
                    if (author_simples.get(k).getAuthorid()==au.getAuthorIds().get(j)){
                        author_simples.get(k).setDocumentcount(author_simples.get(k).getDocumentcount()+1);
                        authorjudge=1;
                        break;
                    }
                }
                if(authorjudge==0){
                    author_simples.add(new author_simple(au.getAuthorIds().get(j),au.getAuthorNames().get(j),1));
                }

                int affjudge=0;//判断机构或机构同义词是否在结果中。
                for (int k=0;k<affiliation_simples.size();k++){
                    String afftoinsert=au.getAffiliationNames().get(j);
                    String affhasin=affiliation_simples.get(k).getAffiliationname();
                    Synonym synonym=word.showSynonym_root(afftoinsert);
                    List<String> tongyici=synonym.getSynonymList();
                    if (affhasin.equals(afftoinsert)||tongyici.contains(affhasin)){
                        //判断已有所有机构的有无该机构或该机构同义词。
                        affiliation_simples.get(k).setDocumentcount(affiliation_simples.get(k).getDocumentcount()+1);
                        affjudge=1;
                        break;
                    }
                }
                if (affjudge==0){
                    affiliation_simples.add(new affiliation_simple(au.getAffiliationIds().get(j),au.getAffiliationNames().get(j),1));
                }
            }

            //计算二十年来每年的论文情况
            if (au.getYear()>2000&&au.getYear()<=2020){
                docbyyears[au.getYear()-2001]=docbyyears[au.getYear()-2001]+1;
            }
            huoyuedu=huoyuedu+getTermActiveness(au.getYear(),au.getRef(),au.getRef());
        }
        long endtime2 = System.currentTimeMillis();
        System.out.println("get time cost " +(endtime2-starttime2) +" ms");
        //下面是排序
        long starttime = System.currentTimeMillis();
        author_simples.sort(new Comparator<author_simple>() {
            @Override
            public int compare(author_simple o1, author_simple o2) {
                if (o1.getDocumentcount()<o2.getDocumentcount()){
                    return 1;
                }
                else if(o1.getDocumentcount()==o2.getDocumentcount()){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        //截取前五项
        if (author_simples.size()>=5){
            author_simples=author_simples.subList(0,5);
        }

        affiliation_simples.sort(new Comparator<affiliation_simple>() {
            @Override
            public int compare(affiliation_simple o1, affiliation_simple o2) {
                if (o1.getDocumentcount()<o2.getDocumentcount()){
                    return 1;
                }
                else if (o1.getDocumentcount()==o2.getDocumentcount()){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        if (affiliation_simples.size()>=5){
            affiliation_simples=affiliation_simples.subList(0,5);
        }

        long endtime =System.currentTimeMillis();
        System.out.println("sort time cost " +(endtime-starttime) +" ms");

        TermViewVo res =new TermViewVo(name,adVos,author_simples,affiliation_simples,docbyyears);
        res.setHuoyuedu(huoyuedu);
        return res;
    }

    @Override
    public termActivenessRankingList showAllList(){

        List<documentForTermActiceness> documents=termmapper.getdocumentForTermActiceness("%%");
        Map<String,Double> termsAndActiveness = new HashMap<>();
        for(documentForTermActiceness d:documents){
            double documentTermActiveness=getTermActiveness(d.getYear(),d.getCitationcount(),d.getReferencecount());
            String[] Terms=d.getIeeeterms().split(";");
            for(String term:Terms){
                if (!term.equals("")) {
                    if (!termsAndActiveness.containsKey(term)) {
                        termsAndActiveness.put(term, documentTermActiveness);
                    } else {
                        termsAndActiveness.put(term, termsAndActiveness.get(term) + documentTermActiveness);
                    }
                }
            }
        }
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(termsAndActiveness.entrySet());
        list.sort(new Comparator<Map.Entry<String,Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        ArrayList<String> termNames=new ArrayList<>();
        ArrayList<Double> termActiveness=new ArrayList<>();
        int p=0;
        for (Map.Entry<String,Double> a:list){
            if (p<20) {
                termNames.add(a.getKey());
                termActiveness.add((double)Math.round(a.getValue()*100)/100);
                p++;
            }
            else {
                break;
            }
        }
        termActivenessRankingList rankingList=new termActivenessRankingList(termNames,termActiveness);
        return rankingList;
    }

    @Override
    public termActivenessRankingList selectRankingListByName(String name){
        List<documentForTermActiceness> documents=termmapper.getdocumentForTermActiceness("%"+name+"%");
        Map<String,Double> termsAndActiveness = new HashMap<>();
        for(documentForTermActiceness d:documents){
            double documentTermActiveness=getTermActiveness(d.getYear(),d.getCitationcount(),d.getReferencecount());
            String[] Terms=d.getIeeeterms().split(";");
            for(String term:Terms){
                //System.out.println(term);
                if ((!term.equals(""))&term.toUpperCase().contains(name.toUpperCase())) {
                    //System.out.println(term);
                    if (!termsAndActiveness.containsKey(term)) {
                        termsAndActiveness.put(term, documentTermActiveness);
                    } else {
                        termsAndActiveness.put(term, termsAndActiveness.get(term) + documentTermActiveness);
                    }
                }
            }
        }
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(termsAndActiveness.entrySet());
        list.sort(new Comparator<Map.Entry<String,Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        ArrayList<String> termNames=new ArrayList<>();
        ArrayList<Double> termActiveness=new ArrayList<>();
        int p=0;
        for (Map.Entry<String,Double> a:list){
            if (p<20) {
                termNames.add(a.getKey());
                termActiveness.add((double)Math.round(a.getValue()*100)/100);
                p++;
            }
            else {
                break;
            }
        }
        termActivenessRankingList rankingList=new termActivenessRankingList(termNames,termActiveness);
        return rankingList;
    }

    @Override
    public termConnectionVo getTermChartByName(String name){

        List<dataNodeVo> nodes=new ArrayList<>();
        List<linkVo> links=new ArrayList<>();
        List<categoryVo> categories=new ArrayList<>();

        //先根据中心点找出和他一起出现的ieeterms
        List<String> allterms=termmapper.getIeeeTermsByNmae("%"+name+"%");


        //用hash来计算出现次数最多的前十个
        Map<String,Integer> termsandcount = new HashMap<>();
        List<String> terms=new ArrayList<>();
        List<Double> activeness=new ArrayList<>();


        terms.add(name);
        for (String allterm:allterms) {
            String[] termsOfDoc =allterm.split(";");
            for (String term:termsOfDoc){
                //System.out.println(term);
                if (!term.equals(name)) {
                    if (!termsandcount.containsKey(term)) {
                        termsandcount.put(term, 1);
                    } else {
                        termsandcount.put(term, termsandcount.get(term) + 1);
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(termsandcount.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int p=0;
        for (Map.Entry<String,Integer> a:list){
            if (p<10) {
                terms.add(a.getKey());
                p++;
            }
            else {
                break;
            }
        }
        //System.out.println(terms.size());
        //计算前十个的活跃度
        for (String term:terms){
            activeness.add(getTermActivenessByIeeeTermname(term));
        }

        //点的创立
        nodes=makeNodes(terms,activeness);

        //边的创立
        if (terms.size()>1) {
            for (int i=1;i<terms.size();i++) {
                linkVo link=new linkVo(terms.get(0),terms.get(i));
                links.add(link);
            }
        }

        //分类的创立
        for (String term:terms){
            categoryVo categoryVo=new categoryVo(term);
            categories.add(categoryVo);
        }

        termConnectionVo graph=new termConnectionVo(nodes,links,categories);
        return graph;
    }

    public double getTermActivenessByIeeeTermname(String name){
        double res=0;
        List<documentForTermActiceness> documents=termmapper.getdocumentForTermActiceness("%"+name+"%");
        for(documentForTermActiceness d:documents){
            double documentTermActiveness=getTermActiveness(d.getYear(),d.getCitationcount(),d.getReferencecount());
            String[] terms=d.getIeeeterms().split(";");
            int judge=0;
            for (String term:terms){
                if (term.equals(name)){
                    judge=1;
                    break;
                }
            }
            if (judge==1){
                res=res+documentTermActiveness;
            }
        }

        return res;
    }

    public double getTermActiveness(int year,int citationcount,int referencecount){
        double res=(1-0.05*(2020-year))*(5+0.8*referencecount+0.2*citationcount);
        res=(double)Math.round(res*100)/100;
        return res;

    }

    public List<dataNodeVo> makeNodes(List<String> terms,List<Double> activnesses){
        List<dataNodeVo> nodes=new ArrayList<>();
        dataNodeVo node=new dataNodeVo(terms.get(0),0,0,activnesses.get(0).intValue(),getSize(activnesses.get(0)),0);
        nodes.add(node);
        if (terms.size()>1) {
            for (int i = 1; i < terms.size(); i++) {
                dataNodeVo thisnode=new dataNodeVo();
                thisnode.setName(terms.get(i));
                thisnode.setCategory(i);
                thisnode.setSymbolSize(getSize(activnesses.get(i)));
                thisnode.setValue(activnesses.get(i).intValue());

                //把360度平均分成size-1份。
                double dengfenjiaodu=360/(terms.size()-1);
                //x=300cosa
                //y=300sina
                int x=(int)(300*Math.cos(dengfenjiaodu*(i-1)));
                int y=(int)(300*Math.sin(dengfenjiaodu*(i-1)));

                thisnode.setX(x);
                thisnode.setY(y);
                nodes.add(thisnode);
            }
        }
        return nodes;
    }

    public int getSize(double value){
        Double a=value*0.01+30.0;
        return a.intValue();
    }
}

