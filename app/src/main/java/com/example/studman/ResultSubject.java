package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultSubject {

    @SerializedName("mbs_id")
    @Expose
    private String mbsId;
    @SerializedName("res_id")
    @Expose
    private String resId;
    @SerializedName("ex_id")
    @Expose
    private String exId;
    @SerializedName("marks_obtain")
    @Expose
    private String marksObtain;
    @SerializedName("sub_id")
    @Expose
    private String subId;
    @SerializedName("total_marks")
    @Expose
    private String totalMarks;
    @SerializedName("passing_marks")
    @Expose
    private String passingMarks;
    @SerializedName("sub_name")
    @Expose
    private String subName;

    public String getMbsId() {
        return mbsId;
    }

    public void setMbsId(String mbsId) {
        this.mbsId = mbsId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public String getMarksObtain() {
        return marksObtain;
    }

    public void setMarksObtain(String marksObtain) {
        this.marksObtain = marksObtain;
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubjectClass(Float per)
    {
        if(per >= 90)
            return "O";
        if(per >= 80)
            return "A+";
        if(per >= 70)
            return "A";
        if(per >= 60)
            return "B";
        if(per >= 50)
            return "C";
        if(per >= 40)
            return "D";
        else
            return "Fail";
    }

}