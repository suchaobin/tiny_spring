package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 切点
 * @date 2022/3/3 3:07 PM
 **/
public interface Pointcut {
    /**
     * 每个切点都要有对应的类过滤器
     *
     * @return 对应的类过滤器
     */
    ClassFilter getClassFilter();

    /**
     * 每个切点都要有对应的方法过滤器
     *
     * @return 对应的方法过滤器
     */
    MethodMatcher getMethodMatcher();
}
