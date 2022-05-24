package com.hoangvu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class JdbcHelper {

    private static final Logger log = Logger.getLogger(JdbcHelper.class.getName());

    private static Connection connection;

    private JdbcHelper() {}

    public static ResultSet query(String sql, Object... param) {
        try {
            log.info(sql);
            PreparedStatement preparedStatement = getPreparedStatement(sql, param);
            return preparedStatement.executeQuery();
        } catch(Exception ex) {
            JdbcConfig.closeConnection();
            return null;
        }
    }

    public static PreparedStatement save(String sql, Object... param) {
        try {
            log.info(sql);
            return getPreparedStatement(sql, param);
        } catch(Exception ex) {
            ex.printStackTrace();
            JdbcConfig.closeConnection();
            return null;
        }
    }

    private static PreparedStatement getPreparedStatement(String sql, Object...params) throws SQLException {
        if (Objects.isNull(connection) || connection.isClosed()) {
            connection = JdbcConfig.openConnection();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        if (Objects.isNull(params)) {
            return preparedStatement;
        }

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }
}