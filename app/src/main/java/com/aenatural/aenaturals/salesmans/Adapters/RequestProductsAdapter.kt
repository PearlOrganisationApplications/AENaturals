package com.aenatural.aenaturals.salesmans.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class RequestProductsAdapter(var data:ArrayList<RetailerDataModel>,var context:Context):RecyclerView.Adapter<RequestViewHolder>() {
    var quantity:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterrequestproductsalesman,parent,false))
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.requestProductsMinusItem.setOnClickListener {
            if(quantity>0)
                quantity-=1
            holder.requestProductsItemQuantityTV.text="quantity : "+quantity.toString()
        }
        holder.requestProductsAddItem.setOnClickListener {
            quantity+=1
            holder.requestProductsItemQuantityTV.text="quantity : "+quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class RequestViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
 var requestProductsItemQuantityTV = itemView.findViewById<TextView>(R.id.requestProductsItemQuantityTV)
 var requestProductsMinusItem = itemView.findViewById<ImageView>(R.id.requestProductsMinusItem)
 var requestProductsAddItem = itemView.findViewById<ImageView>(R.id.requestProductsAddItem)
}