package com.aenatural.aenaturals.distributors.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class DistributorAllOrderAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<AllOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_distributor_allorder,parent,false))
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class AllOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
}