package org.su.tinyioc.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author suchaobin
 * @description url资源
 * @date 2022/3/1 11:24 AM
 **/
public class UrlResource implements Resource {
    /**
     * url
     */
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    /**
     * 获取inputStream
     *
     * @return inputStream
     * @throws IOException io异常
     */
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection connection = url.openConnection();
        connection.connect();
        return connection.getInputStream();
    }
}
