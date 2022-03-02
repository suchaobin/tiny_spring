package org.su.aop;

import org.junit.Test;
import org.su.HelloWorldService;
import org.su.tinyioc.aop.AdvisedSupport;
import org.su.tinyioc.aop.JdkDynamicAopProxy;
import org.su.tinyioc.aop.TargetSource;
import org.su.tinyioc.context.ApplicationContext;
import org.su.tinyioc.context.ClassPathXmlApplicationContext;

/**
 * @author suchaobin
 * @description jdk动态代理测试
 * @date 2022/3/2 5:34 PM
 **/
public class JdkDynamicAopProxyTest {

    @Test
    public void test() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("hey");
        helloWorldService.helloWorld();
        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(helloWorldService, HelloWorldService.class));
        // 2. 设置拦截器(Advice)
        advisedSupport.setMethodInterceptor(new TestInterceptor());
        // 3. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
        // 4. 基于AOP的调用
        proxy.helloWorld();
    }
}
