package com.aenatural.aenaturals.salesmans.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class CartListAdapter(var data:ArrayList<RetailerDataModel>):RecyclerView.Adapter<CartListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        return CartListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptercartitems,parent,false))
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        var quantity:Int = 0
        holder.minusitemcart.setOnClickListener {
            if(quantity>0)
                quantity-=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()
        }
        holder.additemcart.setOnClickListener {
            quantity+=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }

}
class CartListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var minusitemcart = itemView.findViewById<ImageView>(R.id.minusitemcart)
    var additemcart = itemView.findViewById<ImageView>(R.id.additemcart)
    var itemquantityTV = itemView.findViewById<TextView>(R.id.cartitemquantity)
}