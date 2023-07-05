package com.aenatural.aenaturals.apiservices.datamodels


data class OrderHistoryResponse(
    val status: String,
    val history: OrderHistoryData,
    val image_endpoint:String
)

data class OrderHistoryData(
    val orders: List<Order>
)

data class Order(
    val id: Int,
    val quantity: Int,
    val product_details: ProductDetails
)

data class SellHistoryResponse(
    val status: String,
    val history: SellHistoryData,
    val image_endpoint: String
)

data class SellHistoryData(
    val sold: List<SoldItem>
)

data class SoldItem(
    val id: Int,
    val quantity: Int,
    val product_details: ProductDetails
)

data class ProductDetails(
    val id: Int,
    val image: String?,
    val pro_price: String?,
    val prod_name: String,
    val prod_description: String?
)


/*data class Order(
    val id: Int,
    val quantity: Int,
    val product_details: ProductDetails
)


data class ProductDetails(
    val id: Int,
    val image: String?,
    val pro_price: String?,
    val prod_name: String,
    val prod_description: String?
)*/




/*data class OrderHistoryResponse(
    val status: String,
    val history: OrderHistoryData
)

data class OrderHistoryData(
    val orders: List<OrdersHistory>,
    val sold: List<OrdersHistory>
)

data class OrdersHistory(
    val id: Int,
    val quantity: Int,
    val product_details: SoldHistory
)

data class SoldHistory(
    val id: Int,
    val image: String?,
    val pro_price: String?,
    val prod_name: String,
    val prod_description: String?
)*/