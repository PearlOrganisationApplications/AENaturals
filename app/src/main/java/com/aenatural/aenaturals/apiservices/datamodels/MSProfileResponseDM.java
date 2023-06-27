package com.aenatural.aenaturals.apiservices.datamodels;

import com.google.gson.annotations.SerializedName;

public class MSProfileResponseDM {
    @SerializedName("profile")
    public Profile profile;
    @SerializedName("status")
    public String status;
    @SerializedName("profile_status")
    public String profileStatus;
    @SerializedName("message")
    public String message;

    public Profile getProfile() {
        return profile;
    }

    public String getStatus() {
        return status;
    }

    public String getProfileStatus() {
        return profileStatus;
    }

    public String getMessage(){
        return message;
    }
}
