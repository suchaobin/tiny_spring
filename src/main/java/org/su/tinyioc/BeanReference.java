package org.su.tinyioc;

/**
 * @author suchaobin
 * @description Bean引用
 * @date 2022/3/2 1:28 PM
 **/
public class BeanReference {
    /**
     * bean名
     */
    private String name;

    /**
     * bean
     */
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
