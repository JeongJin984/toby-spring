package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    StaticApplicationContext ac = new StaticApplicationContext();

    @Test
    void staticApplicationContextTest() {
        ac.registerSingleton("hello1" ,Hello.class);
        Hello hello = ac.getBean("hello1", Hello.class);
        assertNotNull(hello);
    }

    @Test
    void BeanDefinitionTest() {
        ac.registerSingleton("hello1" ,Hello.class);
        Hello hello = ac.getBean("hello1", Hello.class);
        assertNotNull(hello);

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        ac.registerBeanDefinition("hello2", helloDef);

        Hello hello2 = ac.getBean("hello2", Hello.class);
        assertNotNull(hello2);

        assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
    }

}