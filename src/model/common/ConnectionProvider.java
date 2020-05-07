package model.common;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {

    private static BasicDataSource basicDataSource = new BasicDataSource();
    static {
        basicDataSource.setMaxTotal(10);
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("");
        basicDataSource.setPassword("");
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}
