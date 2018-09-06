package com.singularity.datapersistence.service.inside;

import com.singularity.datapersistence.bean.ExecInfoException;

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
    public ExecInfoException selectSql(Object object);

    /**
     * 删除
     * @param object
     * @return
     */
    public ExecInfoException deleteSql(Object object);

    /**
     * 新增
     * @param object
     * @return
     */
    public ExecInfoException insertSql(List object);

    /**
     * 更新
     * @param object
     * @return
     */
    public ExecInfoException updateSql(Object object);


}
