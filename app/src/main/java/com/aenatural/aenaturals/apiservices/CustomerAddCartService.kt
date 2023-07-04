package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface CustomerAddCartService {
    @FormUrlEncoded
    @POST("cart/add")
    public fun addtoCart(
        @Header("Authorization") authorization:String,
        @Field("product_id") product_id:String
    ): Call<NormalDataModel>
}