package com.example.demo.mapper;

import com.example.demo.Vo.document;
import com.example.demo.po.*;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface documentmapper {
//dddd
     List<author_document> search(@Param("authorname") String name);

     List<author_document> searchasyears(@Param("start") int year1,@Param("end") int year2);

     document_detail searchDetailById(@Param("ID") int ID);

     List<author_document> searchbylist(@Param("author")String  author,@Param("afflication")String afflication,@Param("meeting")String meeting,@Param("keyword")String keyword,@Param("begin") int begin,@Param("end") int end);

     List<author_document> searchAll();

     void insert_author_simple(author_simple as);

     void insert_affiliation_simple(affiliation_simple as);

     void insert_author_document(author_document a);

     void insert_document_detail(document_detail a);

     void insert_document_terms(document_terms a);

     void insert_paperinformation(paperinformation pi);

     int countByAffiliation(@Param("name")String name);

     int countByAffiliationByYear(@Param("name")String name ,@Param("year")int year);

     List<document_detail> searchAllKeys(@Param("name")String name);

     List<au_aff> selectAuAffByDocId(@Param("id") int id );

     List<author_document> searchbyieeeterm(@Param("ieee") String ieee);

     List<String> searchAffByAuthor(@Param("name") String name);

     List<String> searchAuthotByAff(@Param("affname") String affname);

     aut_view selectInfoById(@Param("authorId") int authorId);

     List<aff_view> getTitlesByAff(@Param("affname")String affname);

     int getReferencecountbyid(@Param("id") int id);

     List<author_simple> getAuthorByIeeeTerm(@Param("ieee") String ieee);
     document_detail searchDetailByDocId(@Param("id") int id);

     List<Integer> searchDocIDByAuthor(@Param("authorName") String authorName,@Param("affName")String affName);

     List<author_simple> getAuthorByAff(@Param("affname") String affname);

     String getAffNameByid(@Param("affId")int affId);
}
