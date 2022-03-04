package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 抽象的AOP代理
 * @date 2022/3/4 9:22 AM
 **/
public abstract class AbstractAopProxy implements AopProxy {

    protected AdvisedSupport advised;

    public AbstractAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
}
