package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ColInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.common.Common;
import com.singularity.datapersistence.common.Reflect;
import com.singularity.datapersistence.common.SqlBasicCach;
import com.singularity.datapersistence.service.inside.SqlCreateInterface;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class MysqlSqlCreate implements SqlCreateInterface {

    //日志
    private static Logger logger= LoggerFactory.getLogger(MysqlSqlCreate.class);

    @Autowired
    private SqlBasicCach sqlBasicCach;



    /**
     * 新增语句
     * @param object
     * @param <T>
     * @return
     */
    public <T> String createInsertSql(T object) throws Exception {
        if(object==null){
            throw new Exception("createInsertSql生成新增语句失败\n");
        }
        StringBuilder sb=new StringBuilder();
        String tableName=object.getClass().getSimpleName().toLowerCase();
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(tableName);
        if(sqlBasicInfo==null){
            throw new Exception("createInsertSql生成新增语句失败,"+object.getClass().getName()+"没有增加entity注解\n");
        }
        sb.append(" insert into ");
        sb.append(getTableNameInfo(sqlBasicInfo)+"(");
        //用于拼接values中的'?'个数
        StringBuffer sbv = new StringBuffer(" values ");
        for(ColInfo colInfo : sqlBasicInfo.getCol()){
            sb.append(dealDecorate(colInfo.getName().toLowerCase())+" , ");
        }
        sbv.append(" "+ Common.insertPlaceholder+"; ");
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        sb.append(" ) ");
        sb.append(sbv);
        sb.append(";");
        sqlBasicInfo.setInsertSql(sb.toString());
        return sb.toString();
    }

    /**
     * 更新语句
     * @param object
     * @param <T>
     * @return
     */
    public  <T> String createUpdateSql(T object) throws Exception {
        StringBuilder sb=new StringBuilder();
        if(object==null){
            throw new Exception("createUpdateSql生成更新语句失败！\n");
        }
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(object.getClass().getSimpleName().toLowerCase());
        if(object==null){
            throw new Exception("createUpdateSql生成更新语句失败,"+object.getClass().getName()+"没有标注entity注解\n");
        }
        if(StringUtils.isEmpty(sqlBasicInfo.getPrimaryKey())){
            throw new Exception("createUpdateSql生成更新语句失败,"+object.getClass().getName()+"没有标注id注解\n");
        }
        sb.append(" update ");
        sb.append(getTableNameInfo(sqlBasicInfo));
        sb.append(" set ");
        StringBuffer sqlw = new StringBuffer(" where 1=1 ");
        for(ColInfo colInfo:sqlBasicInfo.getCol()){
            //主键
            if(sqlBasicInfo.getPrimaryKey().equals(colInfo.getName().toLowerCase())){
                sqlw.append(" and "+dealDecorate(colInfo.getName().toLowerCase())+" = ? ");
            }else{
                sb.append(" "+dealDecorate(colInfo.getName())+"= {"+colInfo.getName().toLowerCase()+"} ,");
            }
        }
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        sb.append(sqlw);
        sb.append(";");
        sqlBasicInfo.setUpdateSql(sb.toString());
        return sb.toString();
    }

    /**
     * 新增语句的数据部分
     * @param objs
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createInsertDataSql(List<T> objs)throws Exception{
        if(objs==null  || objs.size()==0){
            throw new Exception("createInsertDataSql插入的数据不能为空");
        }
        Class clazz=objs.get(0).getClass();
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(clazz.getSimpleName().toLowerCase());
        if(sqlBasicInfo==null){
            throw new Exception("createInsertDataSql生成新增数据语句失败,"+clazz.getSimpleName()+"没有增加entity注解\n");
        }
        StringBuffer sb=new StringBuffer();
        List<ColInfo> colInfos= sqlBasicInfo.getCol();
        for (int j = 0; j <=objs.size()-1 ; j++) {
            T t=objs.get(j);
            if(t!=null){
                sb.append("(");
                for (int i = 0; i <=colInfos.size()-1 ; i++) {
                    ColInfo colInfo=colInfos.get(i);
                    String fieldName= colInfo.getName();
                    Object o=Reflect.getFieldValueByName(fieldName,t);
                    sb.append("'"+getValue(o,colInfo.getType())+"'");
                    if(i!=colInfos.size()-1){
                        sb.append(",");
                    }
                }
                sb.append(")");
            }
            if(j!=objs.size()-1 && objs.get(j+1)!=null){
                sb.append(",");
            }
        }
        if(sb!=null){
            return sb.toString();
        }else{
            throw new Exception("createInsertDataSql生成新增数据语句失败\n");
        }
    }

    private String getValue(Object o,Class clazz){
        if(o==null){
            return "";
        }
        if(clazz.equals(Date.class)){
            Timestamp t = new Timestamp(((Date)o).getTime());
            return t.toString();
        }
        return  o.toString();
    }

    private  String dealDecorate(String str){
        return "`"+str.toLowerCase()+"`";
    }

    /**
     * 获取表名
     * @param sqlBasicInfo
     * @return
     */
    private String getTableNameInfo(SqlBasicInfo sqlBasicInfo){
        if(sqlBasicInfo==null){
            logger.error("获取表名失败\n");
            return "";
        }
        return " " +dealDecorate(sqlBasicInfo.getSchema())+"."+dealDecorate(sqlBasicInfo.getTableName())+" ";
    }
}
