package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface MSSaveService {
    @FormUrlEncoded
    @POST("service/add")
    suspend fun addService(
        @Header("Authorization") authorization:String,
        @Field("serv_name") serv_name:String,
        @Field("serv_cost") serv_cost:String,
    ):Call<NormalDataModel>
}