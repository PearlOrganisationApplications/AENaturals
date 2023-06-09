package com.aenatural.aenaturals.apiservices.datamodels

import com.google.gson.annotations.SerializedName

data class AppointmentListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("appointments")
    val appointments: List<Appointment>
)

data class Appointment(
/*    @SerializedName("id")
    val app_id: String,
    @SerializedName("added_by_user_id")
    val added_by_user_id: String,
    @SerializedName("app_date")
    val app_date: String,
    @SerializedName("app_time")
    val app_time: String,
    @SerializedName("app_duration")
    val app_duration: String,
    @SerializedName("app_status")
    val app_status: String,
    @SerializedName("customer_id")
    val customer_id: String,
    @SerializedName("app_reason")
    val app_reason: String,
    @SerializedName("created_at")
    val created_at: String?,
    @SerializedName("updated_at")
    val updated_at: String?
)*/
    val full_name: String,
    val image: String?, // Assuming the image field can be nullable
    val gender: String,
    val mobile: String,
    val dob: String,
    val app_date: String,
    val app_time: String,
    val app_duration: String,
    val app_status: String,
    val app_reason: String
)

