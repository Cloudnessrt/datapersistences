package com.singularity.datapersistence.enums;

public enum ExistEnum {

    del(1,"删除"),
    exist(0,"存在");

    private Integer key;
    private String text;

    private  ExistEnum(Integer key,String text){
        this.key=key;
        this.text=text;
    }


    public Integer getKey() {
        return key;
    }


    public String getText() {
        return text;
    }
}
