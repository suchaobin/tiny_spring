package org.su.tinyioc.aop;

/**
 * @author suchaobin
 * @description 被代理的对象
 * @date 2022/3/2 5:06 PM
 **/
public class TargetSource {
    /**
     * 被代理对象的Class
     */
    private Class<?> targetClass;
    /**
     * 被代理的对象
     */
    private Object target;

    private Class<?>[] interfaces;

    public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.target = target;
        this.targetClass = targetClass;
        this.interfaces = interfaces;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Class<?>... interfaces) {
        this.interfaces = interfaces;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
