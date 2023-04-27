package com.aenatural.aenaturals.customers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class CustomerTrendingAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<CustomerTrendingHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerTrendingHolder {
        return CustomerTrendingHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_trending_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerTrendingHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class CustomerTrendingHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}