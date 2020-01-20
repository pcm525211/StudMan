
package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Institute {

    @SerializedName("ins_id")
    @Expose
    private Integer insId;
    @SerializedName("ins_name")
    @Expose
    private String insName;
    @SerializedName("ins_location")
    @Expose
    private String insLocation;
    @SerializedName("ins_image")
    @Expose
    private String insImage;
    @SerializedName("EntityState")
    @Expose
    private Integer entityState;


    public Integer getInsId() {
        return insId;
    }

    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    public String getInsName() {
        return insName;
    }

    public void setInsName(String insName) {
        this.insName = insName;
    }

    public String getInsLocation() {
        return insLocation;
    }

    public void setInsLocation(String insLocation) {
        this.insLocation = insLocation;
    }

    public String getInsImage() {
        return insImage;
    }

    public void setInsImage(String insImage) {
        this.insImage = insImage;
    }

    public Integer getEntityState() {
        return entityState;
    }

    public void setEntityState(Integer entityState) {
        this.entityState = entityState;
    }



}
