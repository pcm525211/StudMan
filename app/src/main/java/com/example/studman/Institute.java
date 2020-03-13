
package com.example.studman;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Institute {

    @SerializedName("ins_id")
    @Expose
    private Integer insId;
    @SerializedName("inst_name")
    @Expose
    private String insName;
    @SerializedName("photo")
    @Expose
    private String insImage;


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


    public String getInsImage() {
        return insImage;
    }

    public void setInsImage(String insImage) {
        this.insImage = insImage;
    }




}
