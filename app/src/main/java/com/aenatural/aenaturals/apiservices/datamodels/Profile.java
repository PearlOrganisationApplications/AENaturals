package com.aenatural.aenaturals.apiservices.datamodels;

import com.google.gson.annotations.SerializedName;

public class Profile {
    public String email;
    public String username;
    @SerializedName("full_name")
    public String fullName;
    public String image;
    public String gender;
    public String mobile;
    public String qualification;
    public String profession;
    public String experience;
    @SerializedName("appointment_interval")
    public String appointmentInterval;
    public String salutation;

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
