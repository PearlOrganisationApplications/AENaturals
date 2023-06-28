package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.ProfileUpdateResponse
import retrofit2.Call
import retrofit2.http.*

/*
interface MSUpdateProfileApiService {
    @FormUrlEncoded
    @POST("parlour/profile")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Body requestBody: ProfileUpdateRequest
    ): Call<ProfileUpdateResponse>
}*/
interface MSUpdateProfileApiService {
    @FormUrlEncoded
    @POST("parlour/profile")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Field("full_name") fullName: String?,
        @Field("gender") gender: String?,
        @Field("mobile") mobile: String?,
        @Field("qualification") qualification: String?,
        @Field("profession") profession: String?,
        @Field("experience") experience: String?,
        @Field("appointment_interval") appointmentInterval: String?,
        @Field("salutation") salutation: String?
    ): Call<ProfileUpdateResponse>
}