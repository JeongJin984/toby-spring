package springbook.user.connection;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeNewConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost/testdb", "root", "pass");
    }

    @Override
    public DataSource getDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<Driver>) Class.forName("org.mariadb.jdbc.Driver"));
        dataSource.setUrl("jdbc:mariadb://localhost/testdb");
        dataSource.setUsername("root");
        dataSource.setPassword("pass");
        return dataSource;
    }
}
