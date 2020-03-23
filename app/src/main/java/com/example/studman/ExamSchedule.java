package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExamSchedule {

    @SerializedName("es_id")
    @Expose
    private String esId;
    @SerializedName("exam_id")
    @Expose
    private String examId;
    @SerializedName("sub_id")
    @Expose
    private String subId;
    @SerializedName("total_marks")
    @Expose
    private String totalMarks;
    @SerializedName("passing_marks")
    @Expose
    private String passingMarks;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("syllabus")
    @Expose
    private String syllabus;
    @SerializedName("ex_title")
    @Expose
    private String exTitle;
    @SerializedName("sub_name")
    @Expose
    private String subName;

    public String getEsId() {
        return esId;
    }

    public void setEsId(String esId) {
        this.esId = esId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(String passingMarks) {
        this.passingMarks = passingMarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getExTitle() {
        return exTitle;
    }

    public void setExTitle(String exTitle) {
        this.exTitle = exTitle;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

}