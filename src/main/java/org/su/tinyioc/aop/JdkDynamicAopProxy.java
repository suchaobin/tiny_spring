package org.su.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author suchaobin
 * @description 基于JDK的动态代理
 * @date 2022/3/2 5:27 PM
 **/
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 创建代理对象
     *
     * @return 代理对象
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                advised.getTargetSource().getTargetClass(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 拦截器
        MethodInterceptor interceptor = advised.getMethodInterceptor();
        // 拦截器生效
        return interceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
    }
}
