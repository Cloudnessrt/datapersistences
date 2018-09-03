package com.singularity.datapersistence.service.out.impl;

import com.singularity.datapersistence.common.InitSqlTool;
import com.singularity.datapersistence.service.out.DaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MysqlDaoService implements DaoServiceInterface {

    @Autowired
    private InitSqlTool initSqlTool;

    public <T> void insert(T t){

    }

    public <T> void update(T t){

    }

    public <T> void delete(T t){

    }

    public <T> void insertBath(List<T> ts){

    }

    public void query(String sql){

    }
}
