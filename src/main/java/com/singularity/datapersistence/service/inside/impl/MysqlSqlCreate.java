package com.singularity.datapersistence.service.inside.impl;

import com.singularity.datapersistence.bean.ColInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import com.singularity.datapersistence.common.Common;
import com.singularity.datapersistence.common.Reflect;
import com.singularity.datapersistence.common.SqlBasicCach;
import com.singularity.datapersistence.service.inside.SqlCreateInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MysqlSqlCreate implements SqlCreateInterface {

    //日志
    private static Logger logger= LoggerFactory.getLogger(MysqlSqlCreate.class);

    @Autowired
    private SqlBasicCach sqlBasicCach;



    /**
     * 新增语句
     * @param sqlBasicInfo
     * @param <T>
     * @return
     */
    public <T> String createInsertTempleSql(SqlBasicInfo sqlBasicInfo) throws Exception {
        if(sqlBasicInfo==null){
            throw new Exception("createUpdateSql生成更新语句失败！\n");
        }
        StringBuilder sb=new StringBuilder();
        sb.append(" insert into ");
        sb.append(getTableNameInfo(sqlBasicInfo)+"(");
        //用于拼接values中的'?'个数
        StringBuffer sbv = new StringBuffer(" values ");
        for(ColInfo colInfo : sqlBasicInfo.getCol()){
            sb.append(dealDecorate(colInfo.getName().toLowerCase())+" , ");
        }
        sbv.append(" "+ Common.insertPlaceholder+" ; ");
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        sb.append(" ) ");
        sb.append(sbv);
        return sb.toString();
    }



    /**
     * 新增语句的数据部分
     * @param object
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createInsertSql(T object) throws Exception{
        List<T> objs=new ArrayList<>();
        objs.add(object);
        return createInsertSql(objs);
    }


    /**
     * 新增语句的数据部分
     * @param objs
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createInsertSql(List<T> objs)throws Exception{
        if(objs==null  || objs.size()==0){
            throw new Exception("createInsertDataSql插入的数据不能为空");
        }
        Object obj=objs.get(0);
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(obj);
        if(sqlBasicInfo==null){
            throw new Exception("createInsertDataSql生成新增数据语句失败,"+obj.getClass()+"没有增加entity注解\n");
        }
        StringBuffer sb=new StringBuffer();
        List<ColInfo> colInfos= sqlBasicInfo.getCol();
        String insertTemple=sqlBasicInfo.getInsertSql();
        for (int j = 0; j <=objs.size()-1 ; j++) {
            T t=objs.get(j);
            if(t!=null){
                sb.append("(");
                for (int i = 0; i <=colInfos.size()-1 ; i++) {
                    ColInfo colInfo=colInfos.get(i);
                    String fieldName= colInfo.getName();
                    sb.append(getValue(fieldName,t));
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
            return sqlBasicInfo.getInsertSql().replace(Common.insertPlaceholder, sb.toString());
        }else{
            return "";
        }
    }


    /**
     * 更新语句
     * @param sqlBasicInfo
     * @param <T>
     * @return
     */
    public  <T> String createUpdateTempleSql(SqlBasicInfo sqlBasicInfo) throws Exception {
        StringBuilder sb=new StringBuilder();
        if(sqlBasicInfo==null){
            throw new Exception("createUpdateSql生成更新语句失败！\n");
        }
        sb.append(" update ");
        sb.append(getTableNameInfo(sqlBasicInfo));
        sb.append(" set ");
        StringBuffer sqlw = new StringBuffer(" where 1=1 ");
        for(ColInfo colInfo:sqlBasicInfo.getCol()){
            //主键
            if(sqlBasicInfo.getPrimaryKey().equals(colInfo.getName())){
                sqlw.append(" and {"+colInfo.getName()+"} ");
            }else{
                sb.append(" {"+colInfo.getName()+"} ,");
            }
        }
        sb.delete(sb.toString().lastIndexOf(","),sb.length());
        sb.append(sqlw);
        sb.append(";");
        return sb.toString();
    }


    /**
     * 跟新语句的数据部分
     * @param obj
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> String createUpdateSql(T obj)throws Exception{
        Map<String, Object> map=Common.BeanToMap(obj);
        SqlBasicInfo sqlBasicInfo=sqlBasicCach.getSqlCach(obj);
        if(sqlBasicInfo==null){
            throw new Exception("createUpdateDataSql生成跟新数据语句失败,"+obj.getClass()+"没有增加entity注解\n");
        }
        Pattern pattern = Pattern.compile("\\{[a-zA-Z0-9]+\\}");// 匹配的模式
        String updateTemple=sqlBasicInfo.getUpdateSql();
        Matcher m = pattern.matcher(updateTemple);
        String rep="";
        while (m.find()) {
            rep=m.group(0).replace("{","").replace("}","");
            updateTemple=updateTemple.replace("{"+rep+"}",updatePlaceholder(rep,obj));
        }
        return updateTemple;
    }

    /**
     * 更新语句的占位符
     * @param feild 属性名
     * @param obj 实例
     * @return
     * @throws Exception
     */
    private String updatePlaceholder(String feild,Object obj){
        String result=" "+dealDecorate(feild)+" = "+getValue(feild,obj);
        return result;
    }

    /**
     * 获取值
     * @param fieldName 属性名
     * @param object 实例
     * @return
     */
    private String getValue(String fieldName,Object object){
        if (object == null) {
            return "";
        }
        Object value=null;
        ColInfo colInfo = Reflect.getFieldValueByName(fieldName, object);
        if(colInfo!=null){
            Class clazz = colInfo.getType();
            value = colInfo.getValue();
            if (clazz.equals(Date.class)) {
                if(value!=null){
                    Timestamp t = new Timestamp(((Date) value).getTime());
                    value = t.toString();
                }
            }
        }else{
            logger.error(fieldName+"在"+object.getClass()+"获取值失败\n");
        }
        return value == null ? null : "'" + value.toString() + "'";
    }


    /**
     * 添加转换符
     * @param str 字段
     * @return
     */
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
