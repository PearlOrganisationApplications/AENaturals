package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.CategoriesProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductApiService {
    @GET("category/product/{id}")
    suspend fun getProduct(
        @Header("Authorization") token: String,
        @Path("id") categoryId: String
    ): Response<CategoriesProductResponse>
}


