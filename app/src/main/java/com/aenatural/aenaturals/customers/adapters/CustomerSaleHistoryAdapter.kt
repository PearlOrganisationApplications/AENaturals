package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Order
import com.aenatural.aenaturals.apiservices.datamodels.SoldItem
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.bumptech.glide.Glide

class CustomerSaleHistoryAdapter(var data: ArrayList<SoldItem>, var imageEndpoint: String) :
    RecyclerView.Adapter<CustomerHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHistoryViewHolder {
        return CustomerHistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_pending_payments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomerHistoryViewHolder, position: Int) {
        val soldItem = data[position]
        val imageUrl = "$imageEndpoint${soldItem.product_details.image}"

        holder.customerName.text = soldItem.product_details.prod_name
        holder.productName.text = soldItem.product_details.prod_description
        holder.sellPrice.text = soldItem.product_details.pro_price
        holder.orderDate.text = soldItem.quantity.toString()

        if (!(soldItem.product_details.image == null)) {
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(holder.sellItemImage)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CustomerHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var customerName = itemView.findViewById<TextView>(R.id.customerName)
    var productName = itemView.findViewById<TextView>(R.id.productName)
    var orderDate = itemView.findViewById<TextView>(R.id.orderDate)
    var sellPrice = itemView.findViewById<TextView>(R.id.sellPrice)
    var sellItemImage = itemView.findViewById<ImageView>(R.id.sellItemImage)
}
