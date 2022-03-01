package org.su.io;

import org.junit.Test;
import org.su.tinyioc.io.Resource;
import org.su.tinyioc.io.ResourceLoader;

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
        Resource resource = resourceLoader.getResource("tinyioc.xml");
        InputStream inputStream = resource.getInputStream();
        System.err.println(inputStream != null);
    }
}
