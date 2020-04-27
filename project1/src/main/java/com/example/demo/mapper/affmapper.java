package com.example.demo.mapper;

import com.example.demo.po.documentForAuthorActiceness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface affmapper {
     List<Integer> getAuthorsByAffname(@Param("name") String name);
     List<String> getAffNameByPart(@Param("name") String name);
     int getAffIdByName(@Param("affname") String affname);
     List<String> getSyn(@Param("originword")String originword);
}
