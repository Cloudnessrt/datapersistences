package com.singularity.datapersistence.common;


import com.singularity.datapersistence.bean.ExecInfoException;
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
     * @param key 表名
     */
    public  SqlBasicInfo getSqlCach(String key) {
        return sqlBaseCach.get(key);
    }

    /**
     * 获取表基础信息
     * @param object 类
     */
    public  SqlBasicInfo getSqlCach(Object object) {
        return getSqlCach(object.getClass().getSimpleName().toLowerCase());
    }


    /**
     * 修改数据库基础信息
     * @param sqlBasicInfo 数据库基础信息
     * @return
     */
    public ExecInfoException insertOrUpdateSqlBaseCash(SqlBasicInfo sqlBasicInfo){

        try {
            if(sqlBasicInfo!=null){

                this.sqlBaseCach.put(sqlBasicInfo.getTableName().toLowerCase(), sqlBasicInfo);
            }
        }catch (Exception e){
            return ExecInfoException.setExecInfo("加载数据库基础信息异常"+sqlBasicInfo, ConstantEnum.execErrorCode,sqlBasicInfo+"\n",e);
        }
        return ExecInfoException.successExecInfo(sqlBasicInfo);
    }


    /**
     * 删除数据库基础信息
     * @param sqlBasicInfo 数据库基础信息
     * @return
     */
    public ExecInfoException deleteSqlBaseCash(SqlBasicInfo sqlBasicInfo){

        try {
            this.sqlBaseCach.remove(sqlBasicInfo.getTableName().toLowerCase());
        }catch (Exception e){
            return ExecInfoException.setExecInfo("删除数据库基础信息失败"+sqlBasicInfo.toString(), ConstantEnum.execErrorCode,sqlBasicInfo+"\n",e);
        }
        return ExecInfoException.successExecInfo(sqlBasicInfo);
    }



}
