package com.aenatural.aenaturals.apiservices;

import com.aenatural.aenaturals.apiservices.datamodels.MSProfileResponseDM;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MSGetProfileApiService {
    @GET("parlour/profile")
    Call<MSProfileResponseDM> getProfile(@Header("Authorization") String token);
}
