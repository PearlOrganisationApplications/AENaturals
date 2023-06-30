package com.aenatural.aenaturals.apiservices

import com.aenatural.aenaturals.apiservices.datamodels.AppointmentListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MSAppointmentListApiService{
@GET("appointment/list")
suspend fun getAppointmentList(
    @Header("Authorization") bearerToken: String,
    @Query("selected_date") selectedDate: String
): Response<AppointmentListResponse>
}