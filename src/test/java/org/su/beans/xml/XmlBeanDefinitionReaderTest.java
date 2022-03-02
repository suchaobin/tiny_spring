package org.su.beans.xml;

import org.junit.Test;
import org.su.tinyioc.beans.BeanDefinition;
import org.su.tinyioc.beans.io.ResourceLoader;
import org.su.tinyioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author suchaobin
 * @description 从配置中读取BeanDefinition
 * @date 2022/3/1 1:06 PM
 **/
public class XmlBeanDefinitionReaderTest {

    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
        String location = "tinyioc.xml";
        reader.loadBeanDefinitions(location);
        Map<String, BeanDefinition> registry = reader.getRegistry();
        if (registry == null || registry.size() == 0) {
            throw new RuntimeException("can not load BeanDefinitions from [ " + location + "] ");
        }
    }
}
