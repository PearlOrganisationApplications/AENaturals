package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface MSAppointmentCreateApiService {
    @FormUrlEncoded
    @POST("appointment/add")
    fun createAppointment(
        @Header("Authorization") token: String,
        @Field("app_user") appUser: String,
        @Field("app_date") appDate: String,
        @Field("app_time") appTime: String,
        @Field("app_duration") appDuration: String,
        @Field("app_reason") appReason: String,
        @Field("app_from") appFrom: String
    ): Call<NormalDataModel>
}