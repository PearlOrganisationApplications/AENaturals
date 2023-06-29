package com.aenatural.aenaturals.apiservices.datamodels

data class MSListServiceDM(
val status: String,
val services: List<Service>
)

data class Service(
    val serv_id: String,
    val serv_name: String,
    val serv_cost: String,
    val status: String,
    val created_at: String,
    val updated_at: String,
    val added_by_user_id: String

)
