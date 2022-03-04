package org.su.aop;

import org.junit.Test;
import org.su.HelloWorldService;
import org.su.HelloWorldServiceImpl;
import org.su.tinyioc.aop.AdvisedSupport;
import org.su.tinyioc.aop.Cglib2AopProxy;
import org.su.tinyioc.aop.TargetSource;
import org.su.tinyioc.context.ApplicationContext;
import org.su.tinyioc.context.ClassPathXmlApplicationContext;

/**
 * @author suchaobin
 * @description cglib动态代理测试
 * @date 2022/3/4 9:48 AM
 **/
public class Cglib2AopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        // --------- helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("hey");
        helloWorldService.helloWorld();
        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);
        // 2. 设置拦截器(Advice)
        advisedSupport.setMethodInterceptor(new TestInterceptor());
        // 3. 创建代理(Proxy)
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService) cglib2AopProxy.getProxy();
        // 基于AOP的调用
        proxy.helloWorld();
    }
}
