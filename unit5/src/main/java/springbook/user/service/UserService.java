package springbook.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private PlatformTransactionManager transactionManager;
    private UserDao userDao;

    public UserService(PlatformTransactionManager transactionManager, UserDao userDao) {
        this.transactionManager = transactionManager;
        this.userDao = userDao;
    }

    public void addAll(List<User> user) throws SQLException {
        TransactionStatus status = this.transactionManager.getTransaction(
                new DefaultTransactionDefinition()
        );

        try {
            for(User u : user) {
                userDao.add(u);
            }
            this.transactionManager.commit(status);
        } catch (SQLException e) {
            this.transactionManager.rollback(status);
            throw e;
        }
    }
}
