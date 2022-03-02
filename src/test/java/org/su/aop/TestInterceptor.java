package org.su.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author suchaobin
 * @description 测试专用的拦截器
 * @date 2022/3/2 5:38 PM
 **/
public class TestInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.nanoTime();
        System.err.println("hey, the interceptor for test is running");
        Object proceed = invocation.proceed();
        long end = System.nanoTime();
        System.err.println("the interceptor is stop, cost " + (end - start) + " ns");
        return proceed;
    }
}
