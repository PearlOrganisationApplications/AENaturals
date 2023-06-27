package com.aenatural.aenaturals.apiservices.datamodels;

import com.google.gson.annotations.SerializedName;


public class MSProfileResponseDM {
    @SerializedName("profile")
    private Profile profile;
    @SerializedName("status")
    private String status;
    @SerializedName("profile_status")
    private String profileStatus;
    @SerializedName("message")
    private String message;

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

class Profile {
    private String email;
    private String username;
    @SerializedName("full_name")
    private String fullName;
    private String image;
    private String gender;
    private String mobile;
    private String qualification;
    private String profession;
    private String experience;
    @SerializedName("appointment_interval")
    private String appointmentInterval;
    private String salutation;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImage() {
        return image;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getQualification() {
        return qualification;
    }

    public String getProfession() {
        return profession;
    }

    public String getExperience() {
        return experience;
    }

    public String getAppointmentInterval() {
        return appointmentInterval;
    }

    public String getSalutation() {
        return salutation;
    }
}
