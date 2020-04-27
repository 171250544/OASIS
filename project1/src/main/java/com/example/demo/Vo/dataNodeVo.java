package com.example.demo.Vo;

public class dataNodeVo {
    private String name;
    private int x;
    private int y;
    private int value;
    private int symbolSize;
    private int category;

    public dataNodeVo(String name, int x, int y, int value, int symbolSize, int category) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.value = value;
        this.symbolSize = symbolSize;
        this.category = category;
    }

    public dataNodeVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(int symbolSize) {
        this.symbolSize = symbolSize;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
