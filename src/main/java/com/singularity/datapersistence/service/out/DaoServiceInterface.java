package com.singularity.datapersistence.service.out;

import com.singularity.datapersistence.bean.ExecInfo;

import java.util.List;

public interface DaoServiceInterface {

    /**
     * 新增
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfo insert(T t);

    /**
     * 新增集合方式
     * @param ts
     * @param <T>
     * @return
     */
    public <T> ExecInfo insertBatch(List<T> ts);

    /**
     * 修改
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfo update(T t);

    /**
     * 删除
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfo delete(T t);

    /**
     * 查询
     * @param sql
     * @param <T>
     * @return
     */
    public <T> List<T> query(String sql,Class<T> clazz);
}
