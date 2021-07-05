package springbook.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import springbook.user.connection.NConnectionMaker;
import springbook.user.dao.NUserDao;
import springbook.user.dao.UserDao;
import springbook.user.service.UserService;

import java.sql.Driver;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Bean
    public UserService service() throws ClassNotFoundException {

        UserDao nUserDao = new NUserDao(new NConnectionMaker());

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<Driver>) Class.forName("org.mariadb.jdbc.Driver"));
        dataSource.setUrl("jdbc:mariadb://localhost/testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("pass");

        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);

        return new UserService(manager, nUserDao);
    }
}
