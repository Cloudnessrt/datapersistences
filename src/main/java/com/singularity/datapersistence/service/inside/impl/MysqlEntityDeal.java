package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ExecInfoException;
import com.singularity.datapersistence.common.Common;
import com.singularity.datapersistence.enums.ConstantEnum;
import com.singularity.datapersistence.service.inside.SqlEntityDealInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MysqlEntityDeal implements SqlEntityDealInterface {

    //日志
    private static Logger logger= LoggerFactory.getLogger(MysqlEntityDeal.class);

    public MysqlEntityDeal() {
    }

    /**
     * 查询
     * @param object
     * @return
     */
    @Override
    public ExecInfoException selectSql(Object object){
        ExecInfoException execInfoException = ExecInfoException.successExecInfo(object);
        return execInfoException;
    }

    /**
     * 删除
     * @param object
     * @return
     */
    @Override
    public ExecInfoException deleteSql(Object object){
        ExecInfoException execInfoException = ExecInfoException.successExecInfo(object);
        return execInfoException;
    }

    /**
     * 新增
     * @param objects
     * @return
     */
    @Override
    public ExecInfoException insertSql(List objects){
        ExecInfoException execInfoException;
        for(Object item:objects){
            if(!Common.isBaseEntityChildren(item) ){
                return ExecInfoException.setExecInfo(ConstantEnum.notBaseEntityErrorCode,item,null);
            }
        }
        return ExecInfoException.successExecInfo(objects);
    }

    /**
     * 更新
     * @param object
     * @return
     */
    @Override
    public ExecInfoException updateSql(Object object){
        ExecInfoException execInfoException = ExecInfoException.successExecInfo(object);
        return execInfoException;
    }

}
