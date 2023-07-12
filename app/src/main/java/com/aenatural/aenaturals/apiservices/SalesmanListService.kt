package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.SalesmanListDM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface SalesmanListService {
    @GET("salesman/list")
    public fun getSalesmanList(@Header("Authorization") authorization: String):Call<SalesmanListDM>
}