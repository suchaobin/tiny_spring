package org.su.tinyioc.factory;

import org.su.tinyioc.BeanDefinition;

/**
 * @author suchaobin
 * @description 可注入的bean工厂
 * @date 2022/2/28 5:23 PM
 **/
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    /**
     * 根据beanDefinition创建bean对象
     *
     * @param beanDefinition beanDefinition实体
     * @return bean对象
     */
    @Override
    Object doCreateBean(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        if (beanClass == null) {
            throw new RuntimeException("beanClass can not be null");
        }
        try {
            return beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
