package com.aenatural.aenaturals.apiservices.datamodels

data class CustomerProduct(
    val prod_id: String,
    val cat_id: String,
    val image: String?,
    val prod_name: String,
    val pro_price: String,
    val prod_description: String,
    val prod_status: String,
    val created_at: String?,
    val updated_at: String?
)

data class CustomerProductListDM(
    val status: String,
    val products: List<CustomerProduct>
)
