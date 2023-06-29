package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.MSListServiceDM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MSListServices {
    @GET("service/list")
    public fun listServices(
        @Header("Authorization") authorization:String
    ): Call<MSListServiceDM>
}