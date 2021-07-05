package springbook.user.jdbcContext;

import springbook.user.connection.ConnectionMaker;
import springbook.user.statement.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    ConnectionMaker cm;

    public JdbcContext(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void workWithStatementStrategy(StatementStrategy st) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = this.cm.makeNewConnection();
            ps = st.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(ps != null) {
                try { ps.close(); } catch (SQLException ignored) { }
            }
            if(c != null) {
                try { c.close(); } catch (SQLException ignored) { }
            }
        }
     }

     public void executeSql(final String query) throws SQLException {
        workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(query);
                    }
                }
        );
     }
}
