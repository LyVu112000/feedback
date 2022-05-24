package com.hoangvu.jdbc;

import com.hoangvu.mapper.IMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

public class JdbcTemplate<T> {

    private IMapper<T> mapper;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Class<T> clazz;

    public JdbcTemplate(IMapper<T> mapper, Class<T> clazz) {
        this.mapper = mapper;
        this.clazz = clazz;
    }

    public List<T> selectAll() {
        StringBuilder selectAll = new StringBuilder("SELECT * FROM ")
                .append(this.clazz.getSimpleName());
        return this.select(selectAll.toString()).executeQueryList();
    }

    public int deleteBy(String column, Object data) {
        StringBuilder delete = new StringBuilder("DELETE FROM ")
                .append(this.clazz.getSimpleName())
                .append(" WHERE ")
                .append(column)
                .append(" = ?");
        return this.delete(delete.toString(), data).executeDelete();
    }

    public T findOneBy(String column, Object data) {
        StringBuilder findOne = new StringBuilder("SELECT * FROM ")
                .append(this.clazz.getSimpleName())
                .append(" WHERE ")
                .append(column)
                .append(" = ?");
        return this.select(findOne.toString(), data).executeQuery();
    }

    public JdbcTemplate<T> select(String sql, Object...params) {
        this.resultSet = JdbcHelper.query(sql, params);
        return this;
    }

    public JdbcTemplate<T> save(String sql, Object... params) {
        this.preparedStatement = JdbcHelper.save(String.valueOf(sql), params);
        return this;
    }

    private JdbcTemplate<T> delete(String sql, Object... params) {
        this.preparedStatement = JdbcHelper.save(sql, params);
        return this;
    }

    private int executeDelete() {
        return this.executeSave();
    }

    public int executeSave() {
        try {
            this.validateSave();
            return this.preparedStatement.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public T executeQuery() {
        try {
            this.validateQuery();
            this.resultSet.next();
            return this.mapper.map(this.resultSet);
        } catch(Exception ex) {
            return null;
        }
    }

    public List<T> executeQueryList() {
        try {
            this.validateQuery();
            return this.mapper.mapList(this.resultSet);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void validateQuery() {
        if (Objects.isNull(this.resultSet)) {
            throw new IllegalArgumentException("ResultSet is null");
        }

        if (Objects.isNull(this.mapper)) {
            throw new IllegalArgumentException("Mapper is null");
        }
    }

    private void validateSave() {
        if (Objects.isNull(this.preparedStatement)) {
            throw new IllegalArgumentException("Prepared Statement is null");
        }
    }

}