package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class NUserDao implements UserDao {

    ConnectionMaker cm;

    public NUserDao(ConnectionMaker cm) throws SQLException {
        this. cm = cm;    // 책임 과 관심 분리 및 중복 코드 제거
    }

    @Override
    @Transaction
    public void add(User user) throws SQLException {
        // Connection c = DriverManager.getConnection("jdbc:mariadb://localhost/testdb", "root", "pass");
        Connection c = cm.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into user (id, name, password) values (?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    @Override
    public User get(String id) throws SQLException {
        // Connection c = DriverManager.getConnection("jdbc:mariadb://localhost/testdb", "root", "pass");
        Connection c = cm.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from user where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        rs.next();
        User user = new User();

        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
