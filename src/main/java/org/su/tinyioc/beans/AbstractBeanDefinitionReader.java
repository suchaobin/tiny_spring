package org.su.tinyioc.beans;

import org.su.tinyioc.beans.io.ResourceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suchaobin
 * @description 抽象的BeanDefinition加载器
 * @date 2022/3/1 11:35 AM
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    /**
     * key是对应的bean名，val是对应的bean定义对象
     */
    private Map<String, BeanDefinition> registry;

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new ConcurrentHashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
