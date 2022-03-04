package org.su.tinyioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.su.tinyioc.beans.BeanPostProcessor;
import org.su.tinyioc.beans.factory.AbstractBeanFactory;
import org.su.tinyioc.beans.factory.BeanFactory;

import java.util.List;

/**
 * @author suchaobin
 * @description AspectJAwareAdvisor自动代理创建者
 * @date 2022/3/3 4:31 PM
 **/
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        List<AspectJExpressionPointcutAdvisor> beans = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : beans) {
            // 当前这个类能否通过AOP表达式
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                ProxyFactory advisedSupport = new ProxyFactory();
                // 设置代理对象
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                // 设置拦截器
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                // 设置匹配器
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                // 创建代理对象
                return advisedSupport.getProxy();
            }
        }
        return bean;
    }
}
