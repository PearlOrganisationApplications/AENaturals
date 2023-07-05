package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.GetCategoriesDM
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GetProductCategoriesService {
    @GET("category/list")
    public fun getCategories(@Header("Authorization") token:String): Call<GetCategoriesDM>
}
