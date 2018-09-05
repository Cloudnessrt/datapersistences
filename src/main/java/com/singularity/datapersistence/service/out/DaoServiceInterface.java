package com.singularity.datapersistence.service.out;

import com.singularity.datapersistence.bean.ExecInfo;

import java.util.List;

public interface DaoServiceInterface {

    public <T> ExecInfo insert(T t);

    public <T> ExecInfo update(T t);

    public <T> ExecInfo delete(T t);

    public <T> ExecInfo insertBatch(List<T> ts);

    public <T> ExecInfo query(String sql);
}
