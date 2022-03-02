package org.su.tinyioc.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author suchaobin
 * @description 反射方法调用
 * @date 2022/3/2 5:11 PM
 **/
public class ReflectiveMethodInvocation implements MethodInvocation {
    /**
     * 调用方法的对象
     */
    private Object target;

    /**
     * 调用的方法
     */
    private Method method;

    /**
     * 参数
     */
    private Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.args;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }
}
