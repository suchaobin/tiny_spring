package org.su.tinyioc.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author suchaobin
 * @description Resource是spring内部定位资源的接口
 * @date 2022/3/1 11:23 AM
 **/
public interface Resource {
    /**
     * 获取inputStream
     *
     * @return inputStream
     * @throws IOException io异常
     */
    InputStream getInputStream() throws IOException;
}
