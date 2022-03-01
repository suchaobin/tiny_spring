package org.su.tinyioc.factory;

import org.su.tinyioc.BeanDefinition;
import org.su.tinyioc.PropertyValue;

import java.lang.reflect.Field;

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
     * @throws Exception 异常
     */
    @Override
    Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Class<?> beanClass = beanDefinition.getBeanClass();
        if (beanClass == null) {
            throw new RuntimeException("beanClass can not be null");
        }
        // 创建bean对象
        Object bean = createBeanInstance(beanDefinition);
        // 属性注入
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 生成bean实例对象，此时的bean对象的属性还没有注入值
     *
     * @param beanDefinition bean定义对象
     * @return 生成的bean实例对象，此时的bean对象的属性还没有注入值
     * @throws Exception 异常
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
    }

    /**
     * 给bean提供属性注入
     *
     * @param bean           bean对象
     * @param beanDefinition bean定义对象
     * @throws Exception 异常
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(bean, propertyValue.getValue());
        }
    }
}
