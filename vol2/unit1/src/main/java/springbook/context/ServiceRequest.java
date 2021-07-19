package springbook.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ServiceRequest {

    void print() {
        System.out.println("SUCESS!!");
    }
}
