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

    /**
     * bean对应的class
     */
    private Class<?> beanClass;

    /**
     * bean对应的className
     */
    private String beanClassName;

    public BeanDefinition() {

    }

    public void setBean(Object bean) {
        this.bean = bean;
        this.beanClass = bean.getClass();
        this.beanClassName = bean.getClass().getName();
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
        this.beanClassName = beanClass.getName();
    }

    public Object getBean() {
        return bean;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
