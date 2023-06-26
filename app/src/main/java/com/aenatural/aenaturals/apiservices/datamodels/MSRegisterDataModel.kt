package com.aenatural.aenaturals.apiservices.datamodels

data class RegisterRequest(
    val email: String,
    val password: String,
    val pname: String,
    val paddress: String
)

data class RegisterResponse(
    val message: String,
    val status:String,
    val token:String
)
