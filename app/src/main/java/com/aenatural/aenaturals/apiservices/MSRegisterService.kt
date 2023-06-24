package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.RegisterRequest
import com.aenatural.aenaturals.apiservices.datamodels.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MSRegisterService {
    @POST("register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>
}