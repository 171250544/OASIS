package com.example.demo.mapper;

import com.example.demo.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface authormapper {
     List<documentForAuthorActiceness> getdocumentForAuthorActiceness(@Param("name") String name);
     List<documentForAuthorActiceness> getdocumentForAuthorActicenessById(@Param("id") int id);
     int getAffIdByAffName(@Param("affname")String affname);
}
