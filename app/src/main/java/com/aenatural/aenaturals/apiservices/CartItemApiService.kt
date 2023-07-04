package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.ViewCartItemResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CartItemApiService {
    @GET("cart")
    suspend fun getViewCartItems(@Header("Authorization") bearerToken: String): Response<ViewCartItemResponse>
}