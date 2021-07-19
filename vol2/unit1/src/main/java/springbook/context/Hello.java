package springbook.context;

import org.springframework.stereotype.Component;

@Component
public class Hello {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
