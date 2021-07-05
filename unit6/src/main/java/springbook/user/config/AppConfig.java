package springbook.user.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import springbook.user.advice.TransactionAdvice;
import springbook.user.connection.NConnectionMaker;
import springbook.user.dao.NUserDao;
import springbook.user.dao.UserDao;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceImpl;
import springbook.user.service.UserServiceProxy;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean("Proxy")
    public UserService proxyService(PointcutAdvisor advisor) throws ClassNotFoundException {

        UserDao nUserDao = new NUserDao(new NConnectionMaker());

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new UserServiceProxy(nUserDao));
        proxyFactoryBean.addAdvisor(advisor);
        UserService userService = (UserService) proxyFactoryBean.getObject();

        return userService;
    }

    @Bean("NonProxy")
    public UserService service() throws ClassNotFoundException {
        return new UserServiceProxy(new NUserDao(new NConnectionMaker()));
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
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
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
