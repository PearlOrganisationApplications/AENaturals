package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.AddSalemanModel
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AddSalemanApiService {
    @POST("salesman/add")
    @Headers("Content-Type: application/json")
    public fun addSaleman(
        @Header("Authorization") bearerToken: String,
        @Body dataModal: AddSalemanModel?
    ): Call<NormalDataModel>
}