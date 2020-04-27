package com.example.demo.serviceinterface;

import com.example.demo.Vo.AuthorViewVO;
import com.example.demo.Vo.authorActivenessRankingList;

import java.util.List;

public interface authorserviceint {
    public AuthorViewVO getAuthorViewVoById(int authorId);
    public authorActivenessRankingList showAllList();

    public authorActivenessRankingList selectRankingListByName(String name);
}
