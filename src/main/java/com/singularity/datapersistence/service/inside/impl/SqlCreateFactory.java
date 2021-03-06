package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.config.Config;
import com.singularity.datapersistence.service.inside.SqlCreateInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SqlCreateFactory {
    //日志
    private static Logger logger= LoggerFactory.getLogger(SqlCreateFactory.class);

    private SqlCreateInterface factory;

    @Autowired
    private MysqlSqlCreate mysqlSqlCreate;

    @PostConstruct
    public void init(){
        String info="初始化装配SqlCreate失败config配置缺失";
        if(factory==null){

            Config config=Config.getInstance();
            if(config!=null){
                switch (config.getDataBase()){
                    case "mysql":factory=mysqlSqlCreate;
                        break;
                    default:factory=mysqlSqlCreate;
                        break;
                }
            }else{
                System.out.println(info);
            }
            if(factory==null){
                System.out.println(info);
            }
        }
    }

    /**
     * 更新语句
     * @param object
     * @param <T>
     * @return
     */
    public  <T> String createUpdateSql(T object) throws Exception{
        return factory.createUpdateSql(object);
    }

    /**
     * 新增语句
     * @param object
     * @param <T>
     * @return
     */
    public <T> String createInsertSql(T object) throws Exception{
        return factory.createInsertSql(object);
    }

    /**
     * 新增语句
     * @param objects
     * @param <T>
     * @return
     */
    public <T> String createInsertSql(List<T> objects) throws Exception{
        return factory.createInsertSql(objects);
    }

    /**
     * 新增语句的数据部分
     * @param sqlBasicInfo
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createInsertTempleSql(SqlBasicInfo sqlBasicInfo)throws Exception{
        return factory.createInsertTempleSql(sqlBasicInfo);
    }

    /**
     * 新增语句的数据部分
     * @param sqlBasicInfo
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createUpdateTempleSql(SqlBasicInfo sqlBasicInfo)throws Exception{
        return factory.createUpdateTempleSql(sqlBasicInfo);
    }

}
