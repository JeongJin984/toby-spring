package springbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import springbook.user.connection.ConnectionMaker;
import springbook.user.domain.User;
import springbook.user.jdbcContext.JdbcContext;
import springbook.user.statement.StatementStrategy;

import java.sql.*;

public class NUserDao implements UserDao {

    ConnectionMaker cm;
    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;

    public NUserDao(ConnectionMaker cm) throws ClassNotFoundException {
        this.cm = cm;    // 책임 과 관심 분리 및 중복 코드 제거
        this.jdbcContext = new JdbcContext(cm);
        this.jdbcTemplate = new JdbcTemplate(cm.getDataSource());
    }

    @Override
    public void add(User user) throws SQLException {
        Connection c = cm.makeNewConnection();

//        class AddStatement implements StatementStrategy {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into user(id, name, password) values(?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        } // 메서드 내부 클래스

        StatementStrategy st = new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                PreparedStatement ps = c.prepareStatement("insert into user(id, name, password) values(?,?,?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        };  // 익명 클래스

        PreparedStatement ps =st.makePreparedStatement(c);
        ps.executeUpdate();

        ps.close();
        c.close();
    }

    @Override
    public User get(String id) throws SQLException {
        return this.jdbcTemplate.queryForObject(
                "select * from user where id = ?",
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
//        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                return c.prepareStatement("delete from user");
//            }
//        });
        jdbcContext.executeSql("delete from user");
    }
}
