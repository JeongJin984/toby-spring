package springbook.user.connection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeNewConnection() throws SQLException;
    public DataSource getDataSource() throws ClassNotFoundException;
}
