package com.hoangvu.mapper;

import com.hoangvu.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements IMapper<Subject> {

    @Override
    public Subject map(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setCode(resultSet.getString("code"));
        subject.setId(resultSet.getInt("id"));
        subject.setName(resultSet.getString("name"));
        return subject;
    }
}