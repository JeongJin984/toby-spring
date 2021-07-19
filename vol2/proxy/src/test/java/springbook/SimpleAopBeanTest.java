package springbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SimpleAopBeanTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("aopBean.xml");

    @Test
    void aspectJTest() {
        SimpleAopBean aopBean = context.getBean("simpleAopBean", SimpleAopBean.class);
        assertThat(aopBean.getMessage(), is("mySpring"));

        aopBean.setMessage("hahaha");
        assertThat(aopBean.getMessage(), is("hahaha"));

        assertThat(aopBean.getOsName(), is("Linux"));
    }
}