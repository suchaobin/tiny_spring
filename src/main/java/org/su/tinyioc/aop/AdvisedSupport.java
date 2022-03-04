package org.su.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author suchaobin
 * @description 代理相关的元数据
 * @date 2022/3/2 5:20 PM
 **/
public class AdvisedSupport {
    /**
     * 被代理的对象
     */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;

    /**
     * 方法匹配器
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
}
