package com.aenatural.aenaturals.apiservices.datamodels

data class LoginDataModel(
    val status: String,
    val token: String,
    val usertype: String,
    val message:String
)