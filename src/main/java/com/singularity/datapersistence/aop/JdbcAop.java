package com.singularity.datapersistence.aop;

import com.alibaba.fastjson.JSON;
import com.singularity.datapersistence.config.Config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
@Order(2147483647)
@Aspect
@Component
public class JdbcAop {

    @Pointcut("execution(public * org.springframework.jdbc.core.*.*(..))")
    public void sqlLog(){}

    /**
     * sql监控
     * @param joinPoint
     * @throws Throwable
     */
    @Before("sqlLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        Config config=Config.getInstance();
        if(config.isPrintSql()){
            Object[] args = joinPoint.getArgs();//参数
            Signature signature = joinPoint.getSignature();//此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
            MethodSignature methodSignature = (MethodSignature) signature;//获取参数名
            HashMap map=new HashMap();
            for(int i=0;i<methodSignature.getParameterNames().length;i++){
                map.put(methodSignature.getParameterNames()[i],args[i]==null? "":JSON.toJSONString(args[i]));
            }
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())+" jdbcSqlLog--> "+map.toString()+"\n");
        }
    }


}
