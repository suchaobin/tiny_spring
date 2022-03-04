package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 代理工厂
 * @date 2022/3/4 10:09 AM
 **/
public class ProxyFactory extends AdvisedSupport implements AopProxy {

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        return new Cglib2AopProxy(this);
    }
}
