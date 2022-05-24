package com.hoangvu.dao;

import com.hoangvu.entity.Subject;
import com.hoangvu.jdbc.JdbcTemplate;
import com.hoangvu.mapper.IMapper;
import com.hoangvu.mapper.SubjectMapper;
import com.hoangvu.util.ConvertUtil;

import java.util.List;

public class SubjectDAO {

    private JdbcTemplate<Subject> jdbcTemplate;

    public SubjectDAO() {
        IMapper<Subject> subjectMapper = new SubjectMapper();
        this.jdbcTemplate = new JdbcTemplate<>(subjectMapper, Subject.class);
    }

    public List<Subject> findAll() {
        return this.jdbcTemplate
                .selectAll();
    }

    public List<Subject> findByNameLike(String name) {
        return this.jdbcTemplate
                .select("SELECT * FROM subject WHERE name LIKE ? ESCAPE '&'", ConvertUtil.resolveKeySearch(name))
                .executeQueryList();
    }

    public Subject findByCode(String code) {
        return this.jdbcTemplate
                .findOneBy("code", code);
    }
}