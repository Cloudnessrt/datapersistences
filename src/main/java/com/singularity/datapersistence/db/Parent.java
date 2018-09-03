package com.singularity.datapersistence.db;


import com.singularity.datapersistence.annotation.Entity;
import com.singularity.datapersistence.annotation.Ignore;

import java.util.Date;

@Entity
public class Parent extends BaseEntity {

    private String name;

    @Ignore
    private Integer yesar;

    private Date birthday;

    public Parent() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return yesar;
    }

    public void setYear(Integer year) {
        this.yesar = year;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



}
