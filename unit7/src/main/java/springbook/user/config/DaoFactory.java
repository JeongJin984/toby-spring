package springbook.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.user.connection.ConnectionMaker;
import springbook.user.connection.NConnectionMaker;
import springbook.user.dao.NUserDao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DaoFactory {
    @Bean("sqlMap")
    public Map<String, String> sqlMap() {
        Map<String, String> sqlmap = new HashMap<>();

        sqlmap.put("add", "insert into user(id, name, password) values(?,?,?)");
        sqlmap.put("get", "select * from user where id = ?");
        sqlmap.put("delete", "delete from user");

        return sqlmap;
    }
}
