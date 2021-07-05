package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DaoFactory {
    @Bean("userDao")
    public UserDao userDa() throws SQLException {
        return new NUserDao(new NConnectionMaker());
    }
}
