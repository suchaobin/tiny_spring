package org.su.tinyioc.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author suchaobin
 * @description cglib动态代理
 * @date 2022/3/4 9:28 AM
 **/
public class Cglib2AopProxy extends AbstractAopProxy {

    public Cglib2AopProxy(AdvisedSupport advised) {
        super(advised);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private AdvisedSupport advised;

        private org.aopalliance.intercept.MethodInterceptor methodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
            this.methodInterceptor = advised.getMethodInterceptor();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            // 如果方法匹配器空了或者方法匹配器匹配成功了，就走方法拦截器
            if (advised.getMethodMatcher() == null ||
                    advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
                return this.methodInterceptor.invoke(new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy));
            }
            // 走普通的cglib代理方法
            return new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.args);
        }
    }
}
