package org.su;

/**
 * @author suchaobin
 * @description HelloWorldService
 * @date 2022/2/28 4:42 PM
 **/
public class HelloWorldService {

    private String text;

    public void helloWorld() {
        System.out.println(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
