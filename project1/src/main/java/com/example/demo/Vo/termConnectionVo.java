package com.example.demo.Vo;


import java.util.ArrayList;
import java.util.List;

public class termConnectionVo {
    private List<dataNodeVo> data;
    private List<linkVo> links;
    private List<categoryVo> categories;

    public termConnectionVo(List<dataNodeVo> data, List<linkVo> links, List<categoryVo> categories) {
        this.data = data;
        this.links = links;
        this.categories = categories;
    }

    public termConnectionVo() {
    }

    public List<dataNodeVo> getData() {
        return data;
    }

    public void setData(List<dataNodeVo> data) {
        this.data = data;
    }

    public List<linkVo> getLinks() {
        return links;
    }

    public void setLinks(List<linkVo> links) {
        this.links = links;
    }

    public List<categoryVo> getCategories() {
        return categories;
    }

    public void setCategories(List<categoryVo> categories) {
        this.categories = categories;
    }
}
