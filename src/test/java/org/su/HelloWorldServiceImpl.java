package org.su;

/**
 * @author suchaobin
 * @description HelloWorldService
 * @date 2022/2/28 4:42 PM
 **/
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputServiceImpl outputService;

    @Override
    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputServiceImpl outputService) {
        this.outputService = outputService;
    }
}
