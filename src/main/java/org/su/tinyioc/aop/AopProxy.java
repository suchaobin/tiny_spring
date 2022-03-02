package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description AOP代理接口
 * @date 2022/3/2 5:19 PM
 **/
public interface AopProxy {
    /**
     * 获取代理对象
     *
     * @return 代理对象
     */
    Object getProxy();
}
