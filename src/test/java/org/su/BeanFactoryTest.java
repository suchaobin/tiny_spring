package org.su;

import org.junit.Test;
import org.su.tinyioc.BeanDefinition;
import org.su.tinyioc.BeanFactory;

/**
 * @author suchaobin
 * @description bean工厂测试类
 * @date 2022/2/28 4:44 PM
 **/
public class BeanFactoryTest {

    @Test
    public void test() {
        // 1.初始化bean工厂
        BeanFactory beanFactory = new BeanFactory();
        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        beanFactory.registerBeanDefinition("hey", beanDefinition);
        // 3.获取bean并调用方法
        HelloWorldService bean = (HelloWorldService) beanFactory.getBean("hey");
        bean.helloWorld();
    }
}
