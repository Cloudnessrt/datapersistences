package com.singularity.datapersistence.common;


import com.singularity.datapersistence.service.inside.impl.SqlCreateFactory;
import com.singularity.datapersistence.service.inside.impl.SqlEntityDealFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private SqlEntityDealFactory sqlEntityDealFactory;
    @Autowired
    private SqlCreateFactory sqlCreateFactory;

    public void init() throws Exception {
        Config config=Config.getInstance();
        String webBaseRealPath = config.getPath();//需要获取的类的路径
        try {
            Set<Class> classes = Common.getClasses(webBaseRealPath);//包下的所有类
            for (Class clazz:classes) {
                if(SqlReflect.isEfficientClass(clazz)) {
                    sqlBasicCach.insertOrUpdateSqlBaseCash(sqlReflect.getTableInfoByClass(clazz));
                    Object object = clazz.newInstance();
                    sqlEntityDealFactory.insertSql(object);
                    System.out.println(sqlCreateFactory.createInsertSql(object));
                    sqlEntityDealFactory.updateSql(object);
                    System.out.println(sqlCreateFactory.createUpdateSql(object));
                    List a=new ArrayList();
                    a.add(object);
                    System.out.println(sqlCreateFactory.createInsertDataSql(a));
                }
            }
        } catch (Exception e) {
            logger.error("反射类失败\n",e);
        }
    }
}
