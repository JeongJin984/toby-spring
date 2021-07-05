package springbook.user.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;
import springbook.user.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {
    @Autowired
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

        userService.addAll(users);
    }
}