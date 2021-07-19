package springbook.context;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import javax.inject.Provider;

@Configuration
@ComponentScan(basePackages = "springbook.context")
public class AppConfig {
    @Bean
    public ObjectFactoryCreatingFactoryBean serviceRequestFactory() {
        ObjectFactoryCreatingFactoryBean factoryBean =
                new ObjectFactoryCreatingFactoryBean();
        factoryBean.setTargetBeanName("serviceRequest");

        return factoryBean;
    }
}
