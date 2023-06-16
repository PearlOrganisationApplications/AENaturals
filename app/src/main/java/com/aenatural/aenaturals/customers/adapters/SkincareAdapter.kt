package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel

class SkincareAdapter(var data: ArrayList<RetailerDataModel>) :
    RecyclerView.Adapter<SkincareViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkincareViewHolder {
        return SkincareViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_allitem_design,parent,false))
    }

    override fun onBindViewHolder(holder: SkincareViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class SkincareViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
