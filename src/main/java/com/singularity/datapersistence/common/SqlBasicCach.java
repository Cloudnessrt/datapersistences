package com.singularity.datapersistence.common;


import com.singularity.datapersistence.bean.ExecInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.enums.ConstantEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 自动sql框架表数据缓存类
 */
@Component
public class SqlBasicCach {
    //日志
    private static Logger logger= LoggerFactory.getLogger(SqlBasicCach.class);

    //数据库基础信息
    private Map<String, SqlBasicInfo> sqlBaseCach=new HashMap<String, SqlBasicInfo>();

    public Map<String, SqlBasicInfo> getSqlCach() {
        return sqlBaseCach;
    }

    /**
     * 获取表基础信息
     * @param key
     */
    public  SqlBasicInfo getSqlCach(String key) {
        return sqlBaseCach.get(key);
    }


    /**
     * 修改数据库基础信息
     * @param sqlBasicInfo 数据库基础信息
     * @return
     */
    public  ExecInfo insertOrUpdateSqlBaseCash(SqlBasicInfo sqlBasicInfo){

        try {
            if(sqlBasicInfo!=null){
                this.sqlBaseCach.put(sqlBasicInfo.getTableName().toLowerCase(), sqlBasicInfo);
            }
        }catch (Exception e){
            return ExecInfo.setExecInfo("修改数据库基础信息异常"+sqlBasicInfo, ConstantEnum.execErrorCode,sqlBasicInfo+"\n",e);
        }
        return ExecInfo.successExecInfo(sqlBasicInfo);
    }


    /**
     * 删除数据库基础信息
     * @param sqlBasicInfo 数据库基础信息
     * @return
     */
    public ExecInfo deleteSqlBaseCash(SqlBasicInfo sqlBasicInfo){

        try {
            this.sqlBaseCach.remove(sqlBasicInfo.getTableName().toLowerCase());
        }catch (Exception e){
            return ExecInfo.setExecInfo("删除数据库基础信息失败"+sqlBasicInfo.toString(), ConstantEnum.execErrorCode,sqlBasicInfo+"\n",e);
        }
        return ExecInfo.successExecInfo(sqlBasicInfo);
    }



}
