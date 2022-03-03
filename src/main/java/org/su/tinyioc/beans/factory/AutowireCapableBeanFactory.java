package org.su.tinyioc.beans.factory;

import org.su.tinyioc.BeanReference;
import org.su.tinyioc.aop.BeanFactoryAware;
import org.su.tinyioc.beans.BeanDefinition;
import org.su.tinyioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author suchaobin
 * @description 可注入的bean工厂
 * @date 2022/2/28 5:23 PM
 **/
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 给bean提供属性注入
     *
     * @param bean           bean对象
     * @param beanDefinition bean定义对象
     * @throws Exception 异常
     */
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            try {
                // 调用set方法
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                // 如果没有set方法就直接字段反射给字段赋值
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
}
