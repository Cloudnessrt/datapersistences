package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ExecInfo;
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
     * @param objects
     * @return
     */
    public  ExecInfo insertSql(List objects){
        ExecInfo execInfo;
        for(Object item:objects){
            if(!Common.isBaseEntityChildren(item) ){
                return ExecInfo.setExecInfo(ConstantEnum.notBaseEntityErrorCode,item,null);
            }
        }
        return ExecInfo.successExecInfo(objects);
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
