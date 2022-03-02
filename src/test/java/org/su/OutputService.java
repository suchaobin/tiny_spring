package org.su;

/**
 * @author suchaobin
 * @description OutputService
 * @date 2022/3/2 1:27 PM
 **/
public class OutputService {

    private HelloWorldService helloWorldService;

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public void output(String text) {
        System.err.println(helloWorldService != null);
        System.out.println(text);
    }
}
