package org.su.tinyioc.context;

import org.su.tinyioc.beans.factory.AbstractBeanFactory;

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

    }
}
