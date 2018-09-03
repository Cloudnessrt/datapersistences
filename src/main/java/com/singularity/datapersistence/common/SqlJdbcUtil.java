/*
package com.singularity.datapersistence.common;


import com.singularity.datapersistence.bean.ColInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;

import java.lang.reflect.Field;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlJdbcUtil {

    */
/**
     * Jdbc返回数据封装成对象
     * @param clz
     * @param rs
     * @return
     * @throws Exception
     *//*

    public static <T>T setObject(Class<T> clz,ResultSet rs) throws Exception{
        T t = clz.newInstance();
        SqlBasicInfo sqlBasicInfo= SqlBasicCach.getSqlCach(t.getClass().getSimpleName());
        if(sqlBasicInfo==null){
            throw new Exception(t.getClass().getName()+"没有使用entity注解标识");
        }
        for(ColInfo colInfo : sqlBasicInfo.getCol()){
            String fieldName=colInfo.getName();
            Field field=clz.getField(fieldName);
            field.setAccessible(true);
            field.set(t, rs.getObject(fieldName.toLowerCase()));
        }
        return t;
    }

    */
/**
     * 替换 增、删、改 传入的 PreparedStatement 对象的 参数
     * @param t
     * @param pst
     * @throws Exception
     *//*

    @SuppressWarnings("unchecked")
    public static <T>void setPstObject(T t,PreparedStatement pst) throws Exception{
        //得到preparedstatement对象的参数列表
        ParameterMetaData paramMetaData= pst.getParameterMetaData();
        int paramCount = paramMetaData.getParameterCount();
        if(t==null){
            throw new Exception("操作对象为空！");
        }
        Class<T> clz = (Class<T>) t.getClass();
        SqlBasicInfo sqlBasicInfo= SqlBasicCach.getSqlCach(clz.getSimpleName());
        if(sqlBasicInfo==null){
            throw new Exception(clz.getName()+"没有使用entity注解标识");
        }
        int i = 1;
        for (ColInfo colInfo:sqlBasicInfo.getCol()) {
            pst.setObject(i++,clz.getField(colInfo.getName()).get(t));
        }
    }
}
*/
