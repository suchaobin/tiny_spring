package org.su;

import org.su.tinyioc.beans.BeanPostProcessor;

/**
 * @author suchaobin
 * @description bean初始化日志
 * @date 2022/3/3 5:36 PM
 **/
public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " start!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " end!");
        return bean;
    }
}
