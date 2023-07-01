package com.aenatural.aenaturals.common


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

     val mainScope = CoroutineScope(Dispatchers.Main)
    val details = "Please fill all the details"
        private const val BASE_URL = "https://devs.pearl-developer.com/ae/v1/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
