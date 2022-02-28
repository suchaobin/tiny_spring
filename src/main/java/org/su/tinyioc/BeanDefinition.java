package org.su.tinyioc;

/**
 * @author suchaobin
 * @description bean定义
 * @date 2022/2/28 1:26 PM
 **/
public class BeanDefinition {
    /**
     * bean对象
     */
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
