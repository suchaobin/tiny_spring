package org.su;

/**
 * @author suchaobin
 * @description OutputService
 * @date 2022/3/2 1:27 PM
 **/
public class OutputService {

    private HelloWorldService helloWorldService;

    public void setHelloWorldService(HelloWorldServiceImpl helloWorldServiceImpl) {
        this.helloWorldService = helloWorldServiceImpl;
    }

    public void output(String text) {
        if (helloWorldService == null) {
            throw new IllegalArgumentException("helloWorldService is null");
        }
        System.out.println(text);
    }
}
