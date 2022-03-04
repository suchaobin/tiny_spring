package org.su;

/**
 * @author suchaobin
 * @description OutputService
 * @date 2022/3/2 1:27 PM
 **/
public class OutputServiceImpl implements OutputService {

    @Override
    public void output(String text) {
        System.out.println(text);
    }
}
