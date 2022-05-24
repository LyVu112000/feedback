package com.hoangvu.jdbc;

import com.hoangvu.util.ConfigUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class JdbcConfig {

    private static Connection connection;
    private static String username;
    private static String password;
    private static String url;
    private static String driverClassName;

    static {
        username = ConfigUtil.getValueConfig("datasource.username");
        password = ConfigUtil.getValueConfig("datasource.password");
        url = ConfigUtil.getValueConfig("datasource.url");
        driverClassName = ConfigUtil.getValueConfig("datasource.driver-class-name");
    }

    private JdbcConfig() {}

    public static Connection openConnection() {
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            throw new RuntimeException("Jdbc connect fail");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (Objects.nonNull(connection) && !connection.isClosed()) {
                connection.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}