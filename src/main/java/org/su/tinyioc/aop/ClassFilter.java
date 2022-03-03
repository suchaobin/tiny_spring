package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 类过滤器
 * @date 2022/3/3 3:03 PM
 **/
public interface ClassFilter {

    /**
     * 判断该类是否满足AOP中的expression的表达式条件
     *
     * @param targetClass 目标类
     * @return true or false
     */
    boolean matches(Class<?> targetClass);
}
