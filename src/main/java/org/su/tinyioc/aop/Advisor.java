package org.su.tinyioc.aop;


import org.aopalliance.aop.Advice;

/**
 * @author suchaobin
 * @description 通知器
 * @date 2022/3/3 3:08 PM
 **/
public interface Advisor {
    /**
     * 获取对应的通知
     *
     * @return 对应的通知
     */
    Advice getAdvice();
}
