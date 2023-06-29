package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface MSCreateCustomerApiService {
    @FormUrlEncoded
    @POST("customer/add")
    fun createCustomer(
        @Header("Authorization") token: String,
        @Field("full_name") fullName: String,
        @Field("mobile") mobile: String,
        @Field("gender") gender: String,
        @Field("email") email: String,
        @Field("dob") dob: String,
        @Field("appointment_date") appointmentDate: String,
        @Field("appointment_time") appointmentTime: String,
        @Field("appointment_duration") appointmentDuration: String,
        @Field("appointment_reason") appointmentReason: String
    ): Call<NormalDataModel>
}