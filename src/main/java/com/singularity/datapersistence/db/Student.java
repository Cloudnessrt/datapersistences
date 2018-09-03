package com.singularity.datapersistence.db;


import com.singularity.datapersistence.annotation.Entity;

import java.util.Date;

@Entity
public class Student extends BaseEntity {

    private String name;

    private Integer year;

    private Date birthday;

    public Student() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



}
