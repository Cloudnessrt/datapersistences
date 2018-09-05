package com.singularity.datapersistence.common;


import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 初始化类
 */
@Component
public class InitSqlTool {

    //日志
    private static Logger logger= LoggerFactory.getLogger(InitSqlTool.class);

    @Autowired
    private SqlBasicCach sqlBasicCach;

    @Autowired
    private SqlReflect sqlReflect;



    public void init() throws Exception {
        Config config=Config.getInstance();
        String webBaseRealPath = config.getPath();//需要获取的类的路径
        try {
            Set<Class> classes = Common.getClasses(webBaseRealPath);//包下的所有类
            for (Class clazz:classes) {
                if(SqlReflect.isEfficientClass(clazz)) {
                    Object object = clazz.newInstance();
                    SqlBasicInfo sqlBasicInfo=sqlReflect.getTableInfoByClass(clazz);
                    sqlBasicCach.insertOrUpdateSqlBaseCash(sqlBasicInfo);
                }
            }
        } catch (Exception e) {
            logger.error("反射类失败\n",e);
        }
    }
}
