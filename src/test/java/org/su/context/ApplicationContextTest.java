package org.su.context;

import org.junit.Test;
import org.su.HelloWorldServiceImpl;
import org.su.tinyioc.context.ApplicationContext;
import org.su.tinyioc.context.ClassPathXmlApplicationContext;

/**
 * @author suchaobin
 * @description 应用上下文测试类
 * @date 2022/3/2 2:49 PM
 **/
public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldServiceImpl helloWorldServiceImpl = (HelloWorldServiceImpl) applicationContext.getBean("hey");
        helloWorldServiceImpl.helloWorld();
    }
}
