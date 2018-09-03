package com.singularity.datapersistence.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 反射基础类
 */
public class Reflect {
    //日志
    private static Logger logger= LoggerFactory.getLogger(Reflect.class);
    /**
     * 获取一个类的所有字段
     * @param clazz 类
     * @return 字段
     */
    public static List<Field> getAllFieldByClass(Class clazz){
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields()) ;
        return fields;
    }


    /**
     * 获取一个类的所有方法
     * @param clazz 类
     * @return 类
     */
    public static List<Method> getAllMethodByClass(Class clazz){
        List<Method> methods=Arrays.asList(clazz.getMethods());
        return methods;
    }


    /**
     * 获取一个属性的所有注解
     * @param field 属性
     * @return 注解
     */
    public static Annotation[] getAllAnnotationByField(Field field){
        Annotation[] annotations=field.getDeclaredAnnotations();
        return annotations;
    }

    /**
     * 获取一个方法的所有注解
     * @param method 方法
     * @return 注解
     */
    public static Annotation[] getAllAnnotationByMethod(Method method){
        Annotation[] annotations=method.getDeclaredAnnotations();
        return annotations;
    }

    /**
     * 获取一个类的所有注解
     * @param clazz 类
     * @return 注解
     */
    public static Annotation[] getAllAnnotationByClass(Class clazz){
        Annotation[] annotations=clazz.getDeclaredAnnotations();
        return annotations;
    }


    /**
     * 根据属性名获取属性值
     * */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            String info=o.getClass().getTypeName()+"获取"+fieldName+"属性的值失败\n";
            System.out.println(info);
            logger.error(info+e.getMessage(),e);
            return null;
        }
    }

}
