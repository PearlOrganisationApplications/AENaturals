package com.aenatural.aenaturals.apiservices.datamodels

data class Customer(
    val id: String,
    val added_by_user_id: String,
    val image: String?,
    val full_name: String,
    val mobile: String,
    val gender: String,
    val email: String,
    val dob: String,
    val created_at: String?,
    val updated_at: String?
)

data class CustomerListResponse(
    val status: String,
    val customers: List<Customer>
)
