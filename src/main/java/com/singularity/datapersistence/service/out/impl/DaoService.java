package com.singularity.datapersistence.service.out.impl;

import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.common.Common;
import com.singularity.datapersistence.common.SqlBasicCach;
import com.singularity.datapersistence.service.inside.impl.SqlCreateFactory;
import com.singularity.datapersistence.service.inside.impl.SqlEntityDealFactory;
import com.singularity.datapersistence.service.out.DaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DaoService implements DaoServiceInterface {

    @Autowired
    private SqlEntityDealFactory sqlEntityDealFactory;
    @Autowired
    private SqlCreateFactory sqlCreateFactory;
    @Autowired
    private SqlBasicCach sqlBasicCach;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    public <T> void insert(T t) throws Exception {
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            throw new Exception(t.getClass()+"没有被@entity标记无法保存"+"\n");
        }
        sqlEntityDealFactory.insertSql(t);
        String insertSql=sqlBasicInfo.getInsertSql().replace(Common.insertPlaceholder,sqlCreateFactory.createInsertDataSql(t));
        int[] result= jdbcTemplate.batchUpdate(insertSql);
        System.out.println(result);
    }

    public <T> void update(T t){

    }

    public <T> void delete(T t){

    }

    public <T> void insertBath(List<T> ts){

    }

    public void query(String sql){

    }
}
