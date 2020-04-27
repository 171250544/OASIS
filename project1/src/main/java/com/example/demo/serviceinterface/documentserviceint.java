package com.example.demo.serviceinterface;

import com.example.demo.Vo.Synonym;
import com.example.demo.Vo.author_documentVo;
import com.example.demo.Vo.document;
import com.example.demo.Vo.document_detailVo;
import com.example.demo.po.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface documentserviceint {


     public List<author_document> search(String index);
     public List<author_document> searchbylist(String author,String afflication,String meeting,String keyword,String timeBegin,String timeEnd);
     public List<author_documentVo> searchByString(String a);
     public List<author_documentVo> searchByList(String author, String afflication, String meeting, String keyword, String timeBegin, String timeEnd);
     public document_detail searchDetail(int id);
     public List<author_document> searchall();
     public ArrayList<author_simple> searchauthor();
     public List<String> searchaffiliation();
     public int countByAff(String name);
     public int increaseOfThisYear(String name,int year);
     public List<String> searchTopNKeys(String name, int num);
     public List<author_documentVo> getVoByAuthorDocument(List<author_document> aus);
     public List<author_documentVo> searchByIeeeTerm(String ieeeterm);
     public List<paperinformation> searchpaperInformation();
     public document_detailVo searchDetailVo(int id);
     public List<author_simple> getAuthorByIeeeterms(String ieee);
        }
