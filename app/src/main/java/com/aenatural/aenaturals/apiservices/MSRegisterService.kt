package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.ErrorResponse
import com.aenatural.aenaturals.apiservices.datamodels.RegisterRequest
import com.aenatural.aenaturals.apiservices.datamodels.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface MSRegisterService {
    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("pname") pname: String,
        @Field("paddress") paddress: String
    ): Response<RegisterResponse>

    @POST("register")
    suspend fun registerUserError(
        @Body request: RegisterRequest
    ): Response<ErrorResponse>
}