package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ExecInfoException;
import com.singularity.datapersistence.config.Config;
import com.singularity.datapersistence.service.inside.SqlEntityDealInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体处理接口工厂类
 */
@Component
public class SqlEntityDealFactory {

    //日志
    private static Logger logger= LoggerFactory.getLogger(SqlEntityDealFactory.class);

    private SqlEntityDealInterface factory;

    @Autowired
    private MysqlEntityDeal mysqlEntityDeal;

    @PostConstruct
    public void init(){
        if(factory==null){
            Config config=Config.getInstance();
            if(config!=null){
                switch (config.getDataBase()){
                    case "mysql":factory=mysqlEntityDeal;
                        break;
                    default:factory=mysqlEntityDeal;
                        break;
                }
            }else{
                String info="初始化装配EntityDeal失败config配置缺失";
                System.out.println(info);
            }
        }
    }

    /**
     * 查询
     * @param object
     * @return
     */
    
    public ExecInfoException selectSql(Object object){
        return factory.selectSql(object);
    }

    /**
     * 删除
     * @param object
     * @return
     */
    
    public ExecInfoException deleteSql(Object object){
        return factory.deleteSql(object);
    }

    /**
     * 新增
     * @param object
     * @return
     */

    public ExecInfoException insertSql(Object object){
        List objects=new ArrayList();
        objects.add(object);
        return factory.insertSql(objects);
    }

    /**
     * 新增批量
     * @param object
     * @return
     */
    
    public ExecInfoException insertSql(List object){
        return factory.insertSql(object);
    }

    /**
     * 更新
     * @param object
     * @return
     */
    
    public ExecInfoException updateSql(Object object){
        return factory.updateSql(object);
    }



}
