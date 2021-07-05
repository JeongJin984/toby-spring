package springbook.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.user.connection.NConnectionMaker;
import springbook.user.dao.NUserDao;
import springbook.user.dao.UserDao;

import java.sql.SQLException;

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao() throws SQLException, ClassNotFoundException {
        return new NUserDao(new NConnectionMaker());
    }

}
