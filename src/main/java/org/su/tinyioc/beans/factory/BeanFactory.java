package org.su.tinyioc.beans.factory;

/**
 * @author suchaobin
 * @description bean工厂
 * @date 2022/2/28 5:02 PM
 **/
public interface BeanFactory {
    /**
     * 根据bean的name获取对应的bean对象
     *
     * @param name beanName
     * @return bean对象
     */
    Object getBean(String name) throws Exception;
}
