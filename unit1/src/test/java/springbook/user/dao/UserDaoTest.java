package springbook.user.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springbook.user.domain.User;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void daoTest() throws SQLException {
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("njj");
        user.setName("남정진");
        user.setPassword("asdf");

        userDao.add(user);

        System.out.println(user.getId() + "성공!");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
    }
}