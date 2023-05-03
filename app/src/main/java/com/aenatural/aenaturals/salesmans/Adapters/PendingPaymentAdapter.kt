package com.aenatural.aenaturals.salesmans.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel

class PendingPaymentAdapter (var context: Context,var data:ArrayList<SellerDataModel>):RecyclerView.Adapter<PendingPaymentVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingPaymentVH {
        return PendingPaymentVH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_pending_payments,parent,false))
    }

    override fun onBindViewHolder(holder: PendingPaymentVH, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class PendingPaymentVH(itemView: View):RecyclerView.ViewHolder(itemView) {

}
