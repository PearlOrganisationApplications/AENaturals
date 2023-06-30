package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.CustomerListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

/*
interface MSCustomerListApiService {
    @GET("customer/list")
    fun getCustomerList(@Header("Authorization") token: String): Call<CustomerListResponse>
}*/
interface MSCustomerListApiService {
    @GET("customer/list")
    suspend fun getCustomerList(@Header("Authorization") token: String): Response<CustomerListResponse>
}