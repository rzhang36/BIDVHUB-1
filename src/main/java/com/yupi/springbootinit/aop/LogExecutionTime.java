package com.yupi.springbootinit.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 1️⃣ 注解只能用于方法
@Target(ElementType.METHOD)
// 2️⃣ 运行时可用，Spring AOP 需要
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}
