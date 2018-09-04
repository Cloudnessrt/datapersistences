package com.singularity.datapersistence.service.out.impl;

import com.singularity.datapersistence.bean.ExecInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.common.Common;
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
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            String info=t.getClass()+"没有被@entity标记无法保存"+"\n";
            logger.error(info);
            return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,t);
        }
        String info="insert失败";
        try {
            sqlEntityDealFactory.insertSql(t);
            String insertSql = sqlBasicInfo.getInsertSql().replace(Common.insertPlaceholder, sqlCreateFactory.createInsertDataSql(t));
            int[] result = jdbcTemplate.batchUpdate(insertSql);
            return ExecInfo.successExecInfo(result);
        }catch (Exception e){
            logger.error(info);
        }
        return ExecInfo.setExecInfo(info, ConstantEnum.execErrorCode,t);
    }

    public <T> ExecInfo update(T t){
        return ExecInfo.successExecInfo(t);
    }

    public <T> ExecInfo delete(T t){
        return ExecInfo.successExecInfo(t);
    }

    public <T> ExecInfo insertBatch(List<T> ts){
        return ExecInfo.successExecInfo(ts);
    }

    public ExecInfo query(String sql){
        return ExecInfo.successExecInfo(sql);
    }
}
