package org.su.tinyioc.aop;

import org.su.tinyioc.beans.factory.BeanFactory;

/**
 * @author suchaobin
 * @description 由希望知道自己拥有的 BeanFactory 的 bean 实现的接口。
 * @date 2022/3/3 4:26 PM
 **/
public interface BeanFactoryAware {
    /**
     * 设置beanFactory
     *
     * @param beanFactory beanFactory
     * @throws Exception 异常
     */
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
