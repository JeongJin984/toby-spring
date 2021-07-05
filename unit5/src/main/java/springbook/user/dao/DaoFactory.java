package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.user.connection.NConnectionMaker;

import java.sql.SQLException;

@Configuration
public class DaoFactory {
    @Bean("userDao")
    public UserDao userDao() throws SQLException, ClassNotFoundException {
        return new NUserDao(new NConnectionMaker());
    }
}
