package com.aenatural.aenaturals.salesmans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class AllProductsAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<AllProductsViewHolder>() {
    var quantity:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHolder {
        return AllProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterallproductsdesign,parent,false))
    }

    override fun onBindViewHolder(holder: AllProductsViewHolder, position: Int) {
        holder.minusItem.setOnClickListener {
            if(quantity>0)
            quantity-=1
            holder.allproductsquantity.text="quantity : "+quantity.toString()
        }
        holder.addallproducts.setOnClickListener {
            quantity+=1
            holder.allproductsquantity.text="quantity : "+quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class AllProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
var minusItem = itemView.findViewById<ImageView>(R.id.minusallproducts)
var addallproducts = itemView.findViewById<ImageView>(R.id.addallproducts)
var allproductsquantity = itemView.findViewById<TextView>(R.id.allproductsquantity)
}