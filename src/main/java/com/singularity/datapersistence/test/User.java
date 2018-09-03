package com.singularity.datapersistence.test;

public class User {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{" +
                "id:"+getId()+"," +
                "name:"+getName()+
                "}");
        return stringBuilder.toString();
    }
}

