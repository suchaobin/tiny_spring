package org.su.tinyioc.factory;

import org.su.tinyioc.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suchaobin
 * @description 抽象bean工厂
 * @date 2022/2/28 5:04 PM
 **/
public abstract class AbstractBeanFactory implements BeanFactory {
    /**
     * 存放bean的map，key是bean的name，val是对应的beanDefinition实体
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 根据bean的name获取对应的bean对象
     *
     * @param name beanName
     * @return bean对象
     */
    @Override
    public Object getBean(String name) {
        BeanDefinition definition = beanDefinitionMap.get(name);
        return definition == null ? null : definition.getBean();
    }

    /**
     * 将bean注册到bean工厂中
     *
     * @param name           beanName
     * @param beanDefinition bean定义对象
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 根据beanDefinition创建bean对象
     *
     * @param beanDefinition beanDefinition实体
     * @return bean对象
     */
    abstract Object doCreateBean(BeanDefinition beanDefinition);
}
