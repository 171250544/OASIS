package com.example.demo.serviceinterface;

import com.example.demo.Vo.Synonym;

import java.util.List;
import java.util.Map;

public interface wordserviceint {
//    public Synonym showSynonym(String keyword);
    public Synonym showSynonym_root(String keyword);

//    public void createSynonym(Synonym syn);
    public Synonym findSynonym(String keyword);
    public String createSynonym_root (Synonym syn);

}
