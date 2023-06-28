package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.AddBeauticianDM
import com.aenatural.aenaturals.apiservices.datamodels.BeauticianRequestBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface MSAddStaffService {
    @POST("staff/add")
    @FormUrlEncoded
    suspend fun addStaff(@Header("Authorization") authorization: String,
                         @Field("salutation") salutation:String,
                         @Field("full_name") full_name:String,
                         @Field("mobile") mobile:String,
                         @Field("qualification") qualification:String,
                         @Field("profession") profession:String,
                         @Field("experience") experience:String,
                         @Field("appointment_interval") appointment_interval:String,
                         @Field("gender") gender:String, ):AddBeauticianDM
}