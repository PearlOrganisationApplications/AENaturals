package com.aenatural.aenaturals.apiservices.datamodels

data class SalesmanListDM(
    var status:String,
    var salesman:List<SalesmanDetail>,
    var image_endpoint:String
)

data class SalesmanDetail(
    var email:String,
    var username:String,
    var full_name:String,
    var image:String,
    var contact:String,
)
