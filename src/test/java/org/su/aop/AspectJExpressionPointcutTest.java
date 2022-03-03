package org.su.aop;

import org.junit.Test;
import org.su.HelloWorldService;
import org.su.HelloWorldServiceImpl;
import org.su.tinyioc.aop.AspectJExpressionPointcut;

import java.lang.reflect.Method;

/**
 * @author suchaobin
 * @description 切面表达式切点测试类
 * @date 2022/3/3 3:28 PM
 **/
public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter() {
        String expression = "execution(* org.su.*.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        boolean matches = pointcut.matches(HelloWorldService.class);
        System.err.println(matches);
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        String expression = "execution(* org.su.*.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        Method method = HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld");
        boolean matches = pointcut.matches(method, HelloWorldServiceImpl.class);
        System.err.println(matches);
    }
}
