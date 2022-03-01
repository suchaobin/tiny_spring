package org.su;

import org.junit.Test;
import org.su.tinyioc.BeanDefinition;
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

    @Test
    public void test() throws Exception {
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
}
