package com.hoangvu.service;

import com.hoangvu.dao.SubjectDAO;
import com.hoangvu.entity.Subject;

import java.util.List;

public class SubjectService {

    private SubjectDAO subjectDAO;

    public SubjectService() {
        this.subjectDAO = new SubjectDAO();
    }

    public List<Subject> findAll() {
        return this.subjectDAO.findAll();
    }

    public List<Subject> findByNameLike(String name) {
        return this.subjectDAO.findByNameLike(name);
    }

    public Subject findByCode(String code) {
        return this.subjectDAO.findByCode(code);
    }
}