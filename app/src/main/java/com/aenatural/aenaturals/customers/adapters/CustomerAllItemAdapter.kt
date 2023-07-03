package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Product

class CustomerAllItemAdapter(var data:List<RetailerDataModel>):
    RecyclerView.Adapter<CustomerAllItemAdapter.CustomerAllItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAllItemHolder {
        return CustomerAllItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_allitem_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerAllItemHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CustomerAllItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var item_image = itemView
    }
}

/*class CustomerAllItemAdapter(var data:List<Product>, var imageEndpoint: String):
    RecyclerView.Adapter<CustomerAllItemAdapter.CustomerAllItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAllItemHolder {
        return CustomerAllItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_allitem_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerAllItemHolder, position: Int) {
        val product = data[position]
*//*        val imageUrl = "$imageEndpoint${product.image_endpoint}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.item_image)*//*
//        holder.item_image = product.categories
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CustomerAllItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var item_image = itemView.findViewById<ImageView>(R.id.item_image)
        var item_name = itemView.findViewById<TextView>(R.id.item_name)
        var item_description = itemView.findViewById<TextView>(R.id.item_description)
        var item_price = itemView.findViewById<TextView>(R.id.item_price)
    }
}*/
