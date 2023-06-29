package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.BeauticianProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface BeauticianProfileApiService {
    @GET("staff/list")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<BeauticianProfileResponse>
}