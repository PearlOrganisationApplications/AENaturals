package com.aenatural.aenaturals.apiservices.datamodels

data class GetCategoriesDM(
    val status: String,
    val categories: List<Category>,
    val image_endpoint: String,
    val message:String
)

data class Category(
    val cat_id: String,
    val cat_name: String,
    val cat_status: String,
    val cat_subcat_id: String?,
    val created_at: String?,
    val updated_at: String?,
    val image: String
)
