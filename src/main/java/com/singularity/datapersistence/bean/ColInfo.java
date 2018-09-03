package com.singularity.datapersistence.bean;

public class ColInfo {

    //属性名
    private String name;

    //类型
    private Class type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public ColInfo(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public ColInfo() {
    }
}
