package org.su.tinyioc.beans;

/**
 * @author suchaobin
 * @description bean处理器
 * @date 2022/3/3 4:22 PM
 **/
public interface BeanPostProcessor {
    /**
     * 初始化之前做的操作
     *
     * @param bean     bean
     * @param beanName bean的name
     * @return 要使用的 bean 实例，无论是原始的还是包装好的；如果为 null，则不会调用后续的 BeanPostProcessors
     * @throws Exception 异常
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 初始化之后做的操作
     *
     * @param bean     bean
     * @param beanName bean的name
     * @return 要使用的 bean 实例，无论是原始的还是包装好的；如果为 null，则不会调用后续的 BeanPostProcessors
     * @throws Exception 异常
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
