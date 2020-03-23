package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Result {

    @SerializedName("res_id")
    @Expose
    private String resId;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("stud_id")
    @Expose
    private String studId;
    @SerializedName("total_marks")
    @Expose
    private String totalMarks;
    @SerializedName("marks_obtain")
    @Expose
    private String marksObtain;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("res_date")
    @Expose
    private String resDate;
    @SerializedName("coursename")
    @Expose
    private String coursename;
    @SerializedName("ex_title")
    @Expose
    private String exTitle;
    @SerializedName("subjects")
    @Expose
    private ResultSubject[] resultSubjects;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public List<ResultSubject> getResultSubjects() {
        return Arrays.asList( resultSubjects);
    }

    public void setResultSubjects(ResultSubject[] resultSubjects) {
        this.resultSubjects = resultSubjects;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getMarksObtain() {
        return marksObtain;
    }

    public void setMarksObtain(String marksObtain) {
        this.marksObtain = marksObtain;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getResDate() {
        return resDate;
    }

    public void setResDate(String resDate) {
        this.resDate = resDate;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getExTitle() {
        return exTitle;
    }

    public void setExTitle(String exTitle) {
        this.exTitle = exTitle;
    }

}