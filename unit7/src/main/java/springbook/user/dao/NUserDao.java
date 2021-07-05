package springbook.user.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import springbook.user.connection.ConnectionMaker;
import springbook.user.domain.User;

import java.sql.*;
import java.util.Map;

@Component
public class NUserDao implements UserDao {
    private JdbcTemplate jdbcTemplate;
    private Map<String, String> sqlMap;

    public NUserDao(
            @Qualifier("NConnectionMaker")
            ConnectionMaker cm,
            @Qualifier("sqlMap")
            Map<String, String> sqlMap) throws ClassNotFoundException {
        this.jdbcTemplate = new JdbcTemplate(cm.getDataSource());
        this.sqlMap = sqlMap;
    }

    @Override
    public void add(User user) throws SQLException {
        this.jdbcTemplate.update(
                sqlMap.get("add"),
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }

    @Override
    public User get(String id) throws SQLException {
        return this.jdbcTemplate.queryForObject(
                sqlMap.get("get"),
                (rs, i) -> {
                    User user = new User();
                    user.setId(rs.getString("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    return user;
                },
                id
        );
    }

    @Override
    public void deleteAll() throws SQLException {
        this.jdbcTemplate.update(
                sqlMap.get("delete")
        );
    }
}
