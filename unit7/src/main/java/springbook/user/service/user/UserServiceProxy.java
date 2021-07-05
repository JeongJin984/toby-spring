package springbook.user.service.user;

import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceProxy implements UserService {

    UserDao userDao;

    public UserServiceProxy(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void addAll(List<User> user) throws SQLException {
        for (User u : user) {
            userDao.add(u);
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        userDao.deleteAll();
    }
}
