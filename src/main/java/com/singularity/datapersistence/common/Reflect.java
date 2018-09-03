package com.singularity.datapersistence.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 反射基础类
 */
public class Reflect {

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
}
