package springbook.user.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import springbook.user.dao.NUserDao;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;
import springbook.user.service.UserService;
import springbook.user.service.UserServiceImpl;
import springbook.user.service.UserServiceProxy;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {
    @Autowired
    @Qualifier("NonProxy")
    UserService userService;

    @Test
    void test() throws SQLException {
        User user1 = new User();
        user1.setId("njj");
        user1.setName("남정진");
        user1.setPassword("asdf");

        User user2 = new User();
        user2.setId("pjh");
        user2.setName("박정현");
        user2.setPassword("qwer");

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        userService.deleteAll();
        userService.addAll(users);
    }

    @Autowired
    @Qualifier("Proxy")
    UserService userService2;

    @Test
    void proxyTest() throws SQLException {
        User user1 = new User();
        user1.setId("njj");
        user1.setName("남정진");
        user1.setPassword("asdf");

        User user2 = new User();
        user2.setId("pjh");
        user2.setName("박정현");
        user2.setPassword("qwer");

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        userService2.deleteAll();
        userService2.addAll(users);
    }

    @Test
    @Transactional()
    void transactionalTest() throws SQLException {
        User user1 = new User();
        user1.setId("njj");
        user1.setName("남정진");
        user1.setPassword("asdf");

        User user2 = new User();
        user2.setId("pjh");
        user2.setName("박정현");
        user2.setPassword("qwer");

        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        userService.deleteAll();
        userService.addAll(users);
    }
}

