package org.su.tinyioc.context;

import org.su.tinyioc.beans.BeanDefinition;
import org.su.tinyioc.beans.factory.AbstractBeanFactory;
import org.su.tinyioc.beans.factory.AutowireCapableBeanFactory;
import org.su.tinyioc.beans.io.ResourceLoader;
import org.su.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author suchaobin
 * @description xml应用上下文
 * @date 2022/3/2 2:40 PM
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    /**
     * 配置文件的路径
     */
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        reader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> entry : reader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
