package com.aenatural.aenaturals.apiservices.datamodels

data class ViewCartItemResponse(
    val status: String,
    val carts: ArrayList<CartItem>,
    val image_endpoint:String,
    val message: String
)
data class CartItem(
    val prod_id: String,
    val prod_name: String,
    val prod_status: String,
    val created_at: String?,
    val updated_at: String?,
    val cat_id: String,
    val image: String?,
    val pro_price: String?,
    val prod_description:String,
    val quantity: String,
    val cart_id: String
)