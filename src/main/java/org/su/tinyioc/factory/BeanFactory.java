package org.su.tinyioc.factory;

import org.su.tinyioc.BeanDefinition;

/**
 * @author suchaobin
 * @description bean工厂
 * @date 2022/2/28 5:02 PM
 **/
public interface BeanFactory {
    /**
     * 根据bean的name获取对应的bean对象
     *
     * @param name beanName
     * @return bean对象
     */
    Object getBean(String name);

    /**
     * 将bean注册到bean工厂中
     *
     * @param name           bean名
     * @param beanDefinition bean定义对象
     * @throws Exception 异常
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
