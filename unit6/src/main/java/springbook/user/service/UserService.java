package springbook.user.service;

import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public void addAll(List<User> user) throws SQLException;
    public void deleteAll() throws SQLException;

}
