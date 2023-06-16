package com.aenatural.aenaturals.salesmans.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel

class PendingOrdersAdapter (var context: Context,var data:ArrayList<SellerDataModel>):RecyclerView.Adapter<PendingOrderVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderVH {
        return PendingOrderVH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_pending_orders,parent,false))
    }

    override fun onBindViewHolder(holder: PendingOrderVH, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class PendingOrderVH(itemView: View):RecyclerView.ViewHolder(itemView) {
}
