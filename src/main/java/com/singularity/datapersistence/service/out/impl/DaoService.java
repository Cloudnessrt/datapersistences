package com.singularity.datapersistence.service.out.impl;

import com.singularity.datapersistence.bean.ExecInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.common.SqlBasicCach;
import com.singularity.datapersistence.enums.ConstantEnum;
import com.singularity.datapersistence.service.inside.impl.SqlCreateFactory;
import com.singularity.datapersistence.service.inside.impl.SqlEntityDealFactory;
import com.singularity.datapersistence.service.out.DaoServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DaoService implements DaoServiceInterface {
    //日志
    private static Logger logger= LoggerFactory.getLogger(DaoService.class);
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

    public <T> ExecInfo insert(T t) {
        List<T> ts=new ArrayList<>();
        ts.add(t);
        return insertBatch(ts);
    }

    public <T> ExecInfo update(T t){
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            String info=t.getClass()+"没有被@entity标记无法执行更新"+"\n";
            logger.error(info);
            return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,t);
        }
        String info="update失败";
        try {
            sqlEntityDealFactory.updateSql(t);
            String updateSql = sqlCreateFactory.createUpdateSql(t);
            int[] result = jdbcTemplate.batchUpdate(updateSql);
            return ExecInfo.successExecInfo(result);
        }catch (Exception e){
            logger.error(info,e);
        }
        return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,t);
    }

    public <T> ExecInfo delete(T t){

        return update(t);
    }

    public <T> ExecInfo insertBatch(List<T> ts){
        T t=ts.get(0);
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            String info=t.getClass()+"没有被@entity标记无法执行保存"+"\n";
            logger.error(info);
            return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,t);
        }
        String info="insert失败";
        try {
            sqlEntityDealFactory.insertSql(ts);
            String insertSql = sqlCreateFactory.createInsertSql(ts);
            int[] result = jdbcTemplate.batchUpdate(insertSql);
            return ExecInfo.successExecInfo(result);
        }catch (Exception e){
            logger.error(info,e);
        }
        return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,ts);
    }

    public <T> ExecInfo query(String sql){
        return ExecInfo.successExecInfo(sql);
    }
}
