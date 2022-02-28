package org.su.tinyioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suchaobin
 * @description bean工厂
 * @date 2022/2/28 4:39 PM
 **/
public class BeanFactory {
    /**
     * 存放bean的map，key是bean的name，val是对应的beanDefinition实体
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        BeanDefinition definition = beanDefinitionMap.get(name);
        return definition == null ? null : definition.getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition definition) {
        this.beanDefinitionMap.put(name, definition);
    }
}
