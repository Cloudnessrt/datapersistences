package com.singularity.datapersistence.bean;

public class ColInfo {

    //属性名
    private String name;
    //值
    private Object value;
    //类型
    private Class type;


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

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

    public ColInfo(String name, Class type,Object object) {
        this.name = name;
        this.type = type;
        this.value=object;
    }

    public ColInfo(String name, Class type) {
        this.name = name;
        this.type = type;
    }

    public ColInfo() {
    }
}
