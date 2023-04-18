package com.aenatural.aenaturals.salesmans.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class CartListAdapter(var data:ArrayList<RetailerDataModel>):RecyclerView.Adapter<CartListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        return CartListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptercartitems,parent,false))
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return  data.size
    }

}
class CartListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

}