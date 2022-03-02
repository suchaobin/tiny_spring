package org.su;

/**
 * @author suchaobin
 * @description HelloWorldService
 * @date 2022/2/28 4:42 PM
 **/
public class HelloWorldService {

    private String text;

    private OutputService outputService;

    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
