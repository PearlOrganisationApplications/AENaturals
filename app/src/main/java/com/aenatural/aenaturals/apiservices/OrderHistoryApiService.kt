package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.OrderHistoryResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface OrderHistoryApiService {
    @GET("order/history")
    public fun getOrderHistory(@Header("Authorization") authorization: String): Call<OrderHistoryResponse>
}