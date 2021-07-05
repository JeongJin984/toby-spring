package springbook.user.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import springbook.user.domain.Hello;
import springbook.user.domain.User;
import springbook.user.service.user.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
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

    @Autowired
    Hello hello;

    @Test
    void helloTest() {
        hello.print();
    }
}

