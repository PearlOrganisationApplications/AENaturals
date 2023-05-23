package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel

class CustomerOrderHistoryAdapter(var data:ArrayList<SellerDataModel>):RecyclerView.Adapter<CustomerHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHistoryViewHolder {
        return CustomerHistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_order_history_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerHistoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class CustomerOrderHistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}