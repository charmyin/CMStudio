package com.charmyin.hxxc.vo;

public class JobCategoryType {//职位分类类别
    private String id;//编号

    private String name;//名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}