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
        long time = System.nanoTime();
        System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
        Object proceed = invocation.proceed();
        System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
                + " nanoseconds.");
        return proceed;
    }
}
