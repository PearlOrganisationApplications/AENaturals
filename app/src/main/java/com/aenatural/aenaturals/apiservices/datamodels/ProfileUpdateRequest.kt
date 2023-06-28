package com.aenatural.aenaturals.apiservices.datamodels

import com.google.gson.annotations.SerializedName

/*data class ProfileUpdateRequest(
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("qualification") val qualification: String?,
    @SerializedName("profession") val profession: String?,
    @SerializedName("experience") val experience: String?,
    @SerializedName("appointment_interval") val appointment_interval: String?,
    @SerializedName("salutation") val salutation: String?
)*/
data class ProfileUpdateResponse(
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String
)