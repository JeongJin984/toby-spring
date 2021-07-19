package springbook.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;

@Service
public class UserService {
    @Autowired
    Provider<User> provider;

    public void login(String id, String name) {
        User user = provider.get();
        user.setId(id);
        user.setName(name);
    }
}

