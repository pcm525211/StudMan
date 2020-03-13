
package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Institute {

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
    @SerializedName("inst_id")
    @Expose
    private String instId;
    @SerializedName("inst_name")
    @Expose
    private String instName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("address")
    @Expose
    private String address;

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

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
