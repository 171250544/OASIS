package com.example.demo.serviceinterface;

import com.example.demo.Vo.AffViewVO;
import com.example.demo.Vo.AuthorViewVO;
import com.example.demo.Vo.affActivenessRankingList;

public interface affserviceint {
    public AffViewVO getAffrViewVoByID(int id);
    public affActivenessRankingList showAllList();
    public affActivenessRankingList selectRankingListByName(String name);
}
