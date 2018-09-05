package com.singularity.datapersistence.service.inside;

import com.singularity.datapersistence.bean.SqlBasicInfo;

import java.util.List;

public interface SqlCreateInterface {

    /**
     * 更新语句
     * @param sqlBasicInfo
     * @param <T>
     * @return
     */
    public  <T> String createUpdateTempleSql(SqlBasicInfo sqlBasicInfo) throws Exception;

    /**
     * 新增语句
     * @param sqlBasicInfo
     * @param <T>
     * @return
     */
    public <T> String createInsertTempleSql(SqlBasicInfo sqlBasicInfo) throws Exception;

    /**
     * 更新语句
     * @param object
     * @param <T>
     * @return
     */
    public  <T> String createUpdateSql(T object) throws Exception;

    /**
     * 新增语句
     * @param object
     * @param <T>
     * @return
     */
    public <T> String createInsertSql(T object) throws Exception;

    /**
     * 新增语句的数据部分
     * @param objs
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createInsertSql(List<T> objs)throws Exception;


}
