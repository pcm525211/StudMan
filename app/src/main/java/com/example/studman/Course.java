package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("reg_date")
    @Expose
    private String regDate;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("course_id")
    @Expose
    private String courseId;
    @SerializedName("coursename")
    @Expose
    private String coursename;
    @SerializedName("coursedate")
    @Expose
    private String coursedate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("prerequirement")
    @Expose
    private String prerequirement;
    @SerializedName("whatlearn")
    @Expose
    private String whatlearn;
    @SerializedName("skilllevel")
    @Expose
    private String skilllevel;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("thumbnailurl")
    @Expose
    private String thumbnailurl;
    @SerializedName("inst_id")
    @Expose
    private String instId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursedate() {
        return coursedate;
    }

    public void setCoursedate(String coursedate) {
        this.coursedate = coursedate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPrerequirement() {
        return prerequirement;
    }

    public void setPrerequirement(String prerequirement) {
        this.prerequirement = prerequirement;
    }

    public String getWhatlearn() {
        return whatlearn;
    }

    public void setWhatlearn(String whatlearn) {
        this.whatlearn = whatlearn;
    }

    public String getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(String skilllevel) {
        this.skilllevel = skilllevel;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }


}