package com.aenatural.aenaturals.apiservices.datamodels

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CustomerProductListService {
    @GET("product/list")
    public fun productList(
        @Header("Authorization") token: String
    ): Call<CustomerProductListDM>

}