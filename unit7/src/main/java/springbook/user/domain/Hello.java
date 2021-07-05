package springbook.user.domain;

import org.springframework.stereotype.Component;

@Component
public class Hello {
    public void print() {
        System.out.println("hello");
    }
}
