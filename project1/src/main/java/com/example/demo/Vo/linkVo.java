package com.example.demo.Vo;

/**
 * source:边的起点，需要和dataNodeVo中的name保持一致
 * target：边的终点，需要和dataNodeVo中的name保持一致
 */
public class linkVo {
    private String source;
    private String target;

    public linkVo() {
    }

    public linkVo(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
