package com.example.demo.mapper;

import com.example.demo.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface termmapper {

     List<documentForTermActiceness> getdocumentForTermActiceness(@Param("name") String name);

     List<String> getIeeeTermsByNmae(@Param("name") String name);
}
