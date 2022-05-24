package com.hoangvu.dto;

import com.hoangvu.entity.Review;

import java.util.Date;

public class ReviewDTO {

    private String studentName;
    private String subjectName;
    private Date createDate;
    private String content;

    public ReviewDTO(Review review) {
        this.createDate = review.getCreateDate();
        this.content = review.getContent();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}