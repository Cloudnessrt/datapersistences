package com.singularity.datapersistence.common;


import com.singularity.datapersistence.annotation.Entity;
import com.singularity.datapersistence.annotation.Id;
import com.singularity.datapersistence.annotation.Ignore;
import com.singularity.datapersistence.bean.ColInfo;
import com.singularity.datapersistence.bean.SqlBasicInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * 自动sql框架反射拓展类
 */
@Component
public class SqlReflect {



    /**
     * 获取实体类
     * @param clazz 类
     * @return 实体类列表
     */
    public static List<Class> getAllNeedClass(Class clazz){
        List<Class> result=new ArrayList<Class>();
        try {
            boolean hasSuper = true;
            Class superClazz;
            if (clazz != null && isEfficientClass(clazz)) {
                result.add(clazz);
                while (hasSuper) {
                    superClazz = clazz.getSuperclass();
                    if (!result.contains(superClazz)  ) {
                        result.add(superClazz);
                    } else {
                        hasSuper = false;
                    }
                }
            }
        }catch (Exception e){
            System.out.printf("获取"+clazz.getName()+"标记有@Entity注解的实体类失败\n",e);
        }
        return result;
    }

    /**
     * 判断是否是需要转化的实体类
     * @param superClazz 需要转的类的父类
     * @return
     */
    public static boolean isEfficientClass(Class superClazz){
        try{
            if(superClazz !=null && superClazz.getAnnotation(Entity.class) != null){
                return true;
            }
        }catch (Exception e){
            System.out.printf(superClazz.getName()+"判断是否是需要转化的实体类失败\n",e);
        }
        return false;
    }

    /**
     * 获取有效属性
     * @param clazz 类
     * @return 字段
     */
    public SqlBasicInfo getTableInfoByClass(Class clazz){
        SqlBasicInfo sqlBasicInfo =new SqlBasicInfo();
        Config config=Config.getInstance();
        try{
            List<Class> classes=getAllNeedClass(clazz);
            if(classes.size()>0){
                String className=classes.get(0).getName();
                sqlBasicInfo.setTableName(className.substring(className.lastIndexOf(".")+1).toLowerCase());
                sqlBasicInfo.setSchema(config.getSchema());
            }else{
                return null;
            }
            for (int i=classes.size()-1;i>=0;i--){
                getColInfomationByClass(classes.get(i), sqlBasicInfo);
            }
            if(StringUtils.isEmpty(sqlBasicInfo.getPrimaryKey())){
                throw new Exception(clazz.getName()+"不能没有主键\n");
            }
        }catch (Exception e){
            System.out.printf(clazz.getName()+"获取有效属性失败\n",e);
        }
        return sqlBasicInfo;
    }

    /**
     * 获取一个类的所有不被ignore注解标示的字段
     * @param clazz 类
     * @return 有效属性列表
     */
    public static void getColInfomationByClass(Class clazz,SqlBasicInfo sqlBasicInfo){
        List<ColInfo> list= sqlBasicInfo.getCol();
        try {
            List<Field> oldFields = Reflect.getAllFieldByClass(clazz);
            for (Field field : oldFields) {
                if(field.getDeclaredAnnotation(Ignore.class)==null){
                    list.add(new ColInfo(field.getName().toLowerCase(),field.getType()));
                    if(field.getDeclaredAnnotation(Id.class)!=null){
                        if(StringUtils.isEmpty(sqlBasicInfo.getPrimaryKey())){
                            sqlBasicInfo.setPrimaryKey(field.getName());
                        }else{
                            throw new Exception(clazz.getName()+"有多个主键\n");
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.printf("获取"+clazz.getName()+"未标记有@Ignore注解的实体类属性失败\n",e);
        }

    }

}
