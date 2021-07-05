package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeNewConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost/testdb", "root", "pass");
    }
}
