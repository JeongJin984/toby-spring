package springbook.user.config;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springbook.user.advice.TransactionAdvice;
import springbook.user.connection.NConnectionMaker;
import springbook.user.dao.NUserDao;
import springbook.user.service.user.UserService;
import springbook.user.service.user.UserServiceProxy;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@ComponentScan("springbook.user")
@Import(DaoFactory.class)
@Profile("test")
public class AppConfig {

    @Bean("Proxy")
    public UserService proxyService(
            PointcutAdvisor advisor,
            @Autowired @Qualifier("NUserDao") NUserDao userDao
            ) throws ClassNotFoundException {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new UserServiceProxy(userDao));
        proxyFactoryBean.addAdvisor(advisor);

        return (UserService) proxyFactoryBean.getObject();
    }

    @Bean("NonProxy")
    public UserService service(
            @Autowired
            @Qualifier("sqlMap")
            Map<String, String> sqlMap) throws ClassNotFoundException {
        return new UserServiceProxy(new NUserDao(new NConnectionMaker(), sqlMap));
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<Driver>) Class.forName("org.mariadb.jdbc.Driver"));
        dataSource.setUrl("jdbc:mariadb://localhost/testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("pass");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DefaultPointcutAdvisor advisor(PlatformTransactionManager transactionManager) {

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("add*");
        pointcut.addMethodName("delete*");

        TransactionAdvice advice = new TransactionAdvice(transactionManager);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
