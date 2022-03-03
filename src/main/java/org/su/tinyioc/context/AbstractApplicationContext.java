package org.su.tinyioc.context;

import org.su.tinyioc.beans.BeanPostProcessor;
import org.su.tinyioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author suchaobin
 * @description 抽象的应用上下文
 * @date 2022/3/2 2:37 PM
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {
    /**
     * 要通过applicationContext获取bean，所以内部要维护一个beanFactory
     */
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 根据bean的name获取对应的bean对象
     *
     * @param name beanName
     * @return bean对象
     */
    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    /**
     * 加载bean定义对象
     *
     * @param beanFactory bean工厂
     * @throws Exception 异常
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    /**
     * 注册BeanPostProcessor
     *
     * @param beanFactory bean工厂
     * @throws Exception 异常
     */
    @SuppressWarnings("unchecked")
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List<BeanPostProcessor> processors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (BeanPostProcessor processor : processors) {
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    /**
     * 在刷新的时候，给未初始化的bean进行初始化
     *
     * @throws Exception 异常
     */
    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }
}
