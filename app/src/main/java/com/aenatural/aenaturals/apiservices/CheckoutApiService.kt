package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckoutApiService {
    @POST("cart/checkout")
//    @Headers("Content-Type: text/plain")
    @Headers("Content-Type: application/json")
    public fun checkout(
        @Header("Authorization") bearerToken: String,
        @Body jsonList:RequestBody
    ): Call<NormalDataModel>
}