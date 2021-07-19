package springbook.context;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void prototypeTest() {
        Set<ServiceRequest> set = new HashSet<>();
        set.add(context.getBean("serviceRequest", ServiceRequest.class));
        set.add(context.getBean("serviceRequest", ServiceRequest.class));
        assertThat(set.size(), is(2));
    }

    @Test
    void beanTest() {
        Set<Hello> set = new HashSet<>();
        set.add(context.getBean("hello", Hello.class));
        set.add(context.getBean("hello", Hello.class));
        assertThat(set.size(), is(1));
    }

    @Test
    void factoryTest() {
        ObjectFactory<ServiceRequest> factory = new ObjectFactory<ServiceRequest>() {
            @Override
            public ServiceRequest getObject() throws BeansException {
                return context.getBean("serviceRequest", ServiceRequest.class);
            }
        };

        Set<ServiceRequest> set = new HashSet<>();
        set.add(factory.getObject());
        set.add(factory.getObject());

        assertThat(set.size(), is(2));
    }

    @Test
    void basicTest() {
        ServiceRequestProvider provider = context.getBean("serviceRequestProvider", ServiceRequestProvider.class);

        Set<ServiceRequest> set = new HashSet<>();
        set.add(provider.getProvider());
        set.add(provider.getProvider());

        assertThat(set.size(), is(2));
    }
}