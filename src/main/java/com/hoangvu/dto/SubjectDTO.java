package com.hoangvu.dto;

import com.hoangvu.entity.Subject;

public class SubjectDTO {

    private Integer id;
    private String name;
    private String code;
    private boolean isReview;
    private String contentReview;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.code = subject.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getIsReview() {
        return isReview;
    }

    public void setReview(boolean review) {
        isReview = review;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }
}