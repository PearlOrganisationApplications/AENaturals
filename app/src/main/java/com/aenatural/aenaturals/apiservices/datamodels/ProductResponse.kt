package com.aenatural.aenaturals.apiservices.datamodels

data class ProductResponse(
    val status: String,
    val categories: List<Product>,
    val image_endpoint: String?
)

data class Product(
    val prod_id: String,
    val prod_name: String,
    val prod_status: String,
    val created_at: String?,
    val updated_at: String?,
    val cat_id: String
)

