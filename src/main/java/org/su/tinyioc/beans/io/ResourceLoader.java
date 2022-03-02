package org.su.tinyioc.beans.io;

import java.net.URL;

/**
 * @author suchaobin
 * @description 资源加载
 * @date 2022/3/1 11:29 AM
 **/
public class ResourceLoader {

    /**
     * 获取资源
     *
     * @param location 资源所在路径
     * @return 资源
     */
    public Resource getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
