package org.su;

import org.junit.Test;
import org.su.tinyioc.BeanDefinition;
import org.su.tinyioc.factory.AbstractBeanFactory;
import org.su.tinyioc.factory.AutowireCapableBeanFactory;
import org.su.tinyioc.factory.BeanFactory;
import org.su.tinyioc.io.ResourceLoader;
import org.su.tinyioc.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author suchaobin
 * @description bean工厂测试类
 * @date 2022/2/28 4:44 PM
 **/
public class BeanFactoryTest {

    /**
     * 测试懒加载bean
     *
     * @throws Exception 异常
     */
    @Test
    public void testLazy() throws Exception {
        // 1. 读取配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");
        // 2. 初始化bean工厂，并注册bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
        // 3. 获取bean并调用方法
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("hey");
        helloWorldService.helloWorld();
    }

    @Test
    public void testPreInstantiate() throws Exception {
        // 1. 读取配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions("tinyioc.xml");
        // 2. 初始化bean工厂，并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
        // 3. 进行非懒加载的bean的初始化
        beanFactory.preInstantiateSingletons();
        // 4. 获取bean并调用方法
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("hey");
        helloWorldService.helloWorld();
    }
}
