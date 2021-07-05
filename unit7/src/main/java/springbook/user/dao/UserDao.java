package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.SQLException;

public interface UserDao {
    public void add(User user) throws SQLException;
    public User get(String id) throws SQLException;
    public void deleteAll() throws SQLException;
}
