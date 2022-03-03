package org.su.tinyioc.beans.factory;

import org.su.tinyioc.beans.BeanDefinition;
import org.su.tinyioc.beans.BeanPostProcessor;

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

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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
            bean = initializeBean(bean, name);
        }
        return bean;
    }

    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessBeforeInitialization(bean, name);
        }

        /*
         * 这中间空白部分是实现了InitializingBean接口的话，会调用对应的afterPropertiesSet方法
         * 参考org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory的initializeBean方法
         */


        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessAfterInitialization(bean, name);
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

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 根据calss获取对应的bean集合
     *
     * @param type 类型
     * @return bean集合
     * @throws Exception 异常
     */
    @SuppressWarnings("all")
    public List getBeansForType(Class<?> type) throws Exception {
        List<Object> beans = new ArrayList<>();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                beans.add(getBean(name));
            }
        }
        return beans;
    }

    /**
     * 根据beanDefinition创建bean对象
     *
     * @param beanDefinition beanDefinition实体
     * @return bean对象
     * @throws Exception 异常
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Class<?> beanClass = beanDefinition.getBeanClass();
        if (beanClass == null) {
            throw new RuntimeException("beanClass can not be null");
        }
        // 创建bean对象
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        // 属性注入
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 给bean提供属性注入
     *
     * @param bean           bean对象
     * @param beanDefinition bean定义对象
     * @throws Exception 异常
     */
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }

    /**
     * 生成bean实例对象，此时的bean对象的属性还没有注入值
     *
     * @param beanDefinition bean定义对象
     * @return 生成的bean实例对象，此时的bean对象的属性还没有注入值
     * @throws Exception 异常
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
    }
}
