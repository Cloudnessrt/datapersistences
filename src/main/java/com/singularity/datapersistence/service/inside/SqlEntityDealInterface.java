package com.singularity.datapersistence.service.inside;

import com.singularity.datapersistence.bean.ExecInfo;

import java.util.List;

/**
 * 实体处理接口
 */
public interface SqlEntityDealInterface {

    /**
     * 查询
     * @param object
     * @return
     */
    public ExecInfo selectSql(Object object);

    /**
     * 删除
     * @param object
     * @return
     */
    public  ExecInfo deleteSql(Object object);

    /**
     * 新增
     * @param object
     * @return
     */
    public  ExecInfo insertSql(List object);

    /**
     * 更新
     * @param object
     * @return
     */
    public  ExecInfo updateSql(Object object);


}
