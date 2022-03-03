package org.su.tinyioc.beans.xml;

import org.su.tinyioc.BeanReference;
import org.su.tinyioc.beans.AbstractBeanDefinitionReader;
import org.su.tinyioc.beans.BeanDefinition;
import org.su.tinyioc.beans.PropertyValue;
import org.su.tinyioc.beans.io.Resource;
import org.su.tinyioc.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @author suchaobin
 * @description xml的BeanDefinition加载器
 * @date 2022/3/1 11:40 AM
 **/
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 从配置中读取BeanDefinition
     *
     * @param location 资源所在路径
     * @throws Exception 异常
     */
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        Resource resource = super.getResourceLoader().getResource(location);
        doLoadBeanDefinitions(resource.getInputStream());
    }

    /**
     * 加载beanDefinitions
     *
     * @param inputStream 配置文件对应的流
     * @throws Exception 异常
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        // 解析bean
        registerBeanDefinitions(document);
        // 关闭流
        inputStream.close();
    }

    /**
     * 将配置文件中的所有BeanDefinition都注册到父类的registry
     *
     * @param document xml对应的文档实体
     */
    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    /**
     * 解析所有的BeanDefition
     *
     * @param root xml对应的文档实体的root节点
     */
    private void parseBeanDefinitions(Element root) {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 加载BeanDefinition
     *
     * @param element 对应到文档中的<bean></bean标签
     */
    private void processBeanDefinition(Element element) {
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        processProperty(element, beanDefinition);
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 加载bean对应的属性值
     *
     * @param element        对应到文档中的<bean></bean标签
     * @param beanDefinition beanDefinition实体
     */
    private void processProperty(Element element, BeanDefinition beanDefinition) {
        NodeList nodeList = element.getElementsByTagName("property");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element propertyElement = (Element) node;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                    continue;
                }
                String ref = propertyElement.getAttribute("ref");
                if (ref == null || ref.length() == 0) {
                    throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                            + name + "' must specify a ref or value");
                }
                BeanReference beanReference = new BeanReference(ref);
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
            }
        }
    }
}
