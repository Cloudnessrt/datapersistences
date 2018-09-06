package com.singularity.datapersistence.service.out.impl;

import com.singularity.datapersistence.bean.ExecInfoException;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    /**
     * 保存单个
     * @param t 实体
     * @param <T>
     * @return
     */
    @Override
    public <T> ExecInfoException insert(T t) {
        List<T> ts=new ArrayList<>();
        ts.add(t);
        return insertBatch(ts);
    }

    /**
     * 保存多个
     * @param ts 实体集合
     * @param <T>
     * @return
     */
    @Override
    public <T> ExecInfoException insertBatch(List<T> ts){
        T t=ts.get(0);
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            String info=t.getClass()+"没有被@entity标记无法执行保存"+"\n";
            logger.error(info);
            return ExecInfoException.setExecInfo(info, ConstantEnum.execErrorCode,t);
        }
        String info="insert失败";
        try {
            sqlEntityDealFactory.insertSql(ts);
            String insertSql = sqlCreateFactory.createInsertSql(ts);
            int[] result = jdbcTemplate.batchUpdate(insertSql);
            return ExecInfoException.successExecInfo(result);
        }catch (Exception e){
            logger.error(info,e);
        }
        return ExecInfoException.setExecInfo(info, ConstantEnum.execErrorCode,ts);
    }

    /**
     * 更新
     * @param t 实体
     * @param <T>
     * @return
     */
    @Override
    public <T> ExecInfoException update(T t){
        SqlBasicInfo sqlBasicInfo= sqlBasicCach.getSqlCach(t.getClass().getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            String info=t.getClass()+"没有被@entity标记无法执行更新"+"\n";
            logger.error(info);
            return ExecInfoException.setExecInfo(info, ConstantEnum.execErrorCode,t);
        }
        String info="update失败";
        try {
            ExecInfoException execInfoException =sqlEntityDealFactory.updateSql(t);
            if(execInfoException.getResult()){
                String updateSql = sqlCreateFactory.createUpdateSql(t);
                int[] result = jdbcTemplate.batchUpdate(updateSql);
                return ExecInfoException.successExecInfo(result);
            }
        }catch (Exception e){
            logger.error(info,e);
        }
        return ExecInfoException.setExecInfo(info, ConstantEnum.execErrorCode,t);
    }

    /**
     * 删除
     * @param t 实体
     * @param <T>
     * @return
     */
    @Override
    public <T> ExecInfoException delete(T t){

        return update(t);
    }


    /**
     * 查询
     * @param sql
     * @param clazz
     * @return
     */
    @Override
    public <T> List<T> query(String sql,Class<T> clazz){
        List<T> objs = jdbcTemplate.query(sql,new BeanPropertyRowMapper<T>(clazz));
        return objs;
    }

}
