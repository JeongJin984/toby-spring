package springbook.user.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.config.AppConfig;
import springbook.user.config.DaoFactory;
import springbook.user.domain.User;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserDaoTest {

    @Test
    public void daoTest() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();

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