package com.aenatural.aenaturals.apiservices.datamodels

data class BeauticianRequestBody(
    val salutation: String,
    val fullname: String,
    val mobile: String,
    val qualification: String,
    val profession: String,
    val experience: String,
    val intervel: String,
    val gender: String
)
