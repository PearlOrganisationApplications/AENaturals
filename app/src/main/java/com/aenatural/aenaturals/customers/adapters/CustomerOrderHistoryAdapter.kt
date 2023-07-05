package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Order
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.bumptech.glide.Glide

class CustomerOrderHistoryAdapter(var data: ArrayList<Order>, var imageEndpoint: String ) :
    RecyclerView.Adapter<CustomerOrderHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerOrderHistoryViewHolder {
        return CustomerOrderHistoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.customer_order_history_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomerOrderHistoryViewHolder, position: Int) {
        var order = data[position]
        val imageUrl = "$imageEndpoint${order.product_details.image}"
        holder.orderName.text = order.product_details.prod_name
        holder.orderQuantity.text = "Quantity ${order.quantity}"
        holder.orderPrice.text = order.product_details.pro_price
        holder.purchaseDate.text = order.product_details.prod_description
        if (!(order.product_details.image == null)) {
            Glide.with(holder.itemView.context)
                .load(imageUrl)
                .into(holder.orderItemImage)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CustomerOrderHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var orderName = itemView.findViewById<TextView>(R.id.orderName)
    var orderQuantity = itemView.findViewById<TextView>(R.id.orderQuantity)
    var purchaseDate = itemView.findViewById<TextView>(R.id.purchaseDate)
    var orderPrice = itemView.findViewById<TextView>(R.id.orderPrice)
    var orderItemImage = itemView.findViewById<ImageView>(R.id.orderItemImage)
}