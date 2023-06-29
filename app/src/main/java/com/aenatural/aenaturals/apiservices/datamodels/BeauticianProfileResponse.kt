package com.aenatural.aenaturals.apiservices.datamodels

import com.google.gson.annotations.SerializedName

data class BeauticianProfileResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("staff") val staff: List<Staff>
)
data class Staff(
    @SerializedName("id") val id: String,
    @SerializedName("users_location_id") val usersLocationId: String,
    @SerializedName("image") val image: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("qualification") val qualification: String,
    @SerializedName("profession") val profession: String,
    @SerializedName("experience") val experience: String,
    @SerializedName("appointment_interval") val appointmentInterval: String,
    @SerializedName("salutation") val salutation: String,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("added_by_user_id") val addedByUserId: String
)