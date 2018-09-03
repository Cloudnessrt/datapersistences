package com.singularity.datapersistence.service.out;

import java.util.List;

public interface DaoServiceInterface {

    public <T> void insert(T t);

    public <T> void update(T t);

    public <T> void delete(T t);

    public <T> void insertBath(List<T> ts);

    public void query(String sql);
}
