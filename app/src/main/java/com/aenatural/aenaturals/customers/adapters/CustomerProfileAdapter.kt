package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel

class CustomerProfileAdapter(var data:List<RetailerDataModel>) :RecyclerView.Adapter<CustomerHistoryVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHistoryVH {
        return CustomerHistoryVH(LayoutInflater.from(parent.context).inflate(R.layout.order_history_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerHistoryVH, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class CustomerHistoryVH(itemView: View):RecyclerView.ViewHolder(itemView) {

}
