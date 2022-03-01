package org.su.tinyioc;

/**
 * @author suchaobin
 * @description 从配置中读取BeanDefinition
 * @date 2022/3/1 11:33 AM
 **/
public interface BeanDefinitionReader {

    /**
     * 从配置中读取BeanDefinition
     *
     * @param location 资源所在路径
     * @throws Exception 异常
     */
    void loadBeanDefinitions(String location) throws Exception;
}
