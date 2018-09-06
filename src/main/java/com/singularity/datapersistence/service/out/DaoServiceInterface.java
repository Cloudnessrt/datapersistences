package com.singularity.datapersistence.service.out;

import com.singularity.datapersistence.bean.ExecInfoException;

import java.util.List;

public interface DaoServiceInterface {

    /**
     * 新增
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfoException insert(T t);

    /**
     * 新增集合方式
     * @param ts
     * @param <T>
     * @return
     */
    public <T> ExecInfoException insertBatch(List<T> ts);

    /**
     * 修改
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfoException update(T t);

    /**
     * 删除
     * @param t
     * @param <T>
     * @return
     */
    public <T> ExecInfoException delete(T t);

    /**
     * 查询
     * @param sql sql
     * @param <T> 类型
     * @return 查询结果
     */
    public <T> List<T> query(String sql,Class<T> clazz);
}
