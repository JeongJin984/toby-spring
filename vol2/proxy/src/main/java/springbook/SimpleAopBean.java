package springbook;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.Properties;

public class SimpleAopBean {

    private String message;
    private String osName;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }
}
