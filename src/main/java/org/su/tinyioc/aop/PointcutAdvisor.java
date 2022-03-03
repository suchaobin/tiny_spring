package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 切点通知器
 * @date 2022/3/3 3:10 PM
 **/
public interface PointcutAdvisor extends Advisor {
    /**
     * 获取切点
     *
     * @return 切点
     */
    Pointcut getPointcut();
}
