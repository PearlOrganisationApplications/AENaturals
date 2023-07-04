package com.aenatural.aenaturals.apiservices.datamodels

import com.google.gson.annotations.SerializedName

data class CategoriesProductResponse(
    val status: String,
    val categories: List<CategoriesProduct>,
    val image_endpoint: String,
    val message: String
)

data class CategoriesProduct(
    val prod_id: String,
    val prod_name: String,
    val prod_status: String,
    val created_at: String?,
    val updated_at: String?,
    val cat_id: String,
    @SerializedName("image")
    val cat_image: String?,
    @SerializedName("pro_price")
    val prodPrice: String?

)

