package org.su.beans.io;

import org.junit.Test;
import org.su.tinyioc.beans.io.Resource;
import org.su.tinyioc.beans.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author suchaobin
 * @description 资源加载器测试类
 * @date 2022/3/1 11:59 AM
 **/
public class ResourceLoaderTest {

    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        String location = "tinyioc.xml";
        Resource resource = resourceLoader.getResource(location);
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            throw new RuntimeException("can not load resource from [ " + location + " ]");
        }
    }
}
