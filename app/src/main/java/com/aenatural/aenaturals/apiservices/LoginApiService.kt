package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.LoginDataModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email")email:String,
        @Field("password")password:String
    ):Response<LoginDataModel>
}