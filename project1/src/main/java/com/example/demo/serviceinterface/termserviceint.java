package com.example.demo.serviceinterface;

import com.example.demo.Vo.TermViewVo;
import com.example.demo.Vo.termActivenessRankingList;
import com.example.demo.Vo.termConnectionVo;

public interface termserviceint {

     public TermViewVo getTermViewVoByName(String name);

     public termActivenessRankingList showAllList();

     public termActivenessRankingList selectRankingListByName(String name);

     public termConnectionVo getTermChartByName(String name);
}
