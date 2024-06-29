package mypackage.database_connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DataBaseConnectionPool {

    private static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/TESTDB";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "admin";


    static {
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USER_NAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(12);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

}
