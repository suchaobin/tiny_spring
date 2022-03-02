package org.su.tinyioc.factory;

import org.su.tinyioc.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
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
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 存放非懒加载的bean的name
     */
    private final List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * 根据bean的name获取对应的bean对象
     *
     * @param name beanName
     * @return bean对象
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition definition = beanDefinitionMap.get(name);
        if (definition == null) {
            throw new IllegalArgumentException("no bean named " + name + " is defined");
        }
        Object bean = definition.getBean();
        if (bean == null) {
            bean = doCreateBean(definition);
        }
        return bean;
    }

    /**
     * 将bean注册到bean工厂中
     *
     * @param name           bean名
     * @param beanDefinition bean定义对象
     * @throws Exception 异常
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        // 这边是做测试，所以全部都放到非懒加载的集合中，在Test类中做区别测试
        beanDefinitionNames.add(name);
    }

    /**
     * 初始化非懒加载的bean
     *
     * @throws Exception 异常
     */
    public void preInstantiateSingletons() throws Exception {
        for (String name : this.beanDefinitionNames) {
            getBean(name);
        }
    }

    /**
     * 根据beanDefinition创建bean对象
     *
     * @param beanDefinition beanDefinition实体
     * @return bean对象
     * @throws Exception 异常
     */
    abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
