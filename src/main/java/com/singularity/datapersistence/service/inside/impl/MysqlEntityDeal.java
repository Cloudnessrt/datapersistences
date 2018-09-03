package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ExecInfo;
import com.singularity.datapersistence.common.Common;
import com.singularity.datapersistence.enums.ConstantEnum;
import com.singularity.datapersistence.service.inside.SqlEntityDealInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MysqlEntityDeal implements SqlEntityDealInterface {

    //日志
    private static Logger logger= LoggerFactory.getLogger(MysqlEntityDeal.class);

    /**
     * 查询
     * @param object
     * @return
     */
    public ExecInfo selectSql(Object object){
        ExecInfo execInfo= ExecInfo.successExecInfo(object);
        return execInfo;
    }

    /**
     * 删除
     * @param object
     * @return
     */
    public  ExecInfo deleteSql(Object object){
        ExecInfo execInfo= ExecInfo.successExecInfo(object);
        return execInfo;
    }

    /**
     * 新增
     * @param object
     * @return
     */
    public  ExecInfo insertSql(Object object){
        ExecInfo execInfo;
        if(Common.isBaseEntityChildren(object) ){
            execInfo= ExecInfo.successExecInfo(object);

        }else{
            execInfo=ExecInfo.setExecInfo(ConstantEnum.notBaseEntityErrorCode,object,null);
        }
        return execInfo;
    }

    /**
     * 更新
     * @param object
     * @return
     */
    public  ExecInfo updateSql(Object object){
        ExecInfo execInfo= ExecInfo.successExecInfo(object);
        return execInfo;
    }

}
