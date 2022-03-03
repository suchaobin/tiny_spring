package org.su.tinyioc.aop;

import java.lang.reflect.Method;

/**
 * @author suchaobin
 * @description 方法过滤
 * @date 2022/3/3 3:05 PM
 **/
public interface MethodMatcher {

    /**
     * 判断该方法是否满足AOP中的expression表达式条件
     *
     * @param method      方法
     * @param targetClass 目标类
     * @return true or false
     */
    boolean matches(Method method, Class<?> targetClass);
}
