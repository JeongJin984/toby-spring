package springbook;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SimpleAspect {
    @Before("execution(* springbook.SimpleAopBean.set*(..))")
    public void before() {
        System.out.println("beforeSet: ");
    }

    @After("execution(* springbook.SimpleAopBean.get*(..))")
    public void after() {
        System.out.println("AfterGet: ");
    }
}
