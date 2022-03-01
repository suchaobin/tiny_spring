package org.su;

import org.junit.Test;
import org.su.tinyioc.BeanDefinition;
import org.su.tinyioc.PropertyValue;
import org.su.tinyioc.PropertyValues;
import org.su.tinyioc.factory.AutowireCapableBeanFactory;
import org.su.tinyioc.factory.BeanFactory;

/**
 * @author suchaobin
 * @description bean工厂测试类
 * @date 2022/2/28 4:44 PM
 **/
public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        // 1.初始化bean工厂
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("org.su.HelloWorldService");
        // 3.设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "hello world"));
        beanDefinition.setPropertyValues(propertyValues);
        // 4.注册到bean工厂中，并生成对应bean
        beanFactory.registerBeanDefinition("hey", beanDefinition);
        // 5.获取bean并调用方法测试
        HelloWorldService bean = (HelloWorldService) beanFactory.getBean("hey");
        bean.helloWorld();
    }
}
