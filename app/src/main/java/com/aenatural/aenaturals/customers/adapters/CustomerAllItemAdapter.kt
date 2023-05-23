package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class CustomerAllItemAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<CustomerAllItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAllItemHolder {
        return CustomerAllItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_allitem_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerAllItemHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class CustomerAllItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}