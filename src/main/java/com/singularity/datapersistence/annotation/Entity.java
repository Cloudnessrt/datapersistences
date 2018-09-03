package com.singularity.datapersistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 实体标注注解
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Documented
public @interface Entity {


}
