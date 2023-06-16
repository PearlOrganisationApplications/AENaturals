package com.aenatural.aenaturals.salesmans.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel

class CustomerAdapter(var data:List<RetailerDataModel>):
    RecyclerView.Adapter<CustomerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_list,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class CustomerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}
