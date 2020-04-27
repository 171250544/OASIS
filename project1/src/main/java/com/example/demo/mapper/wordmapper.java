package com.example.demo.mapper;

import com.example.demo.po.similar_word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface wordmapper {
    void insert_similarwords(similar_word sw);
    void insert_similarwords_root(similar_word sw);
    List<String> findSynByRoot(@Param("originword") String originword);

    List<String> selectAllAff();
    List<String> selectAff(@Param("name")String name);

}
