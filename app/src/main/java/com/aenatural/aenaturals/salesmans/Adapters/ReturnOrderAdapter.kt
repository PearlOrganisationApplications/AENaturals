package com.aenatural.aenaturals.salesmans.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel

class ReturnOrderAdapter (var context: Context,var data:ArrayList<SellerDataModel>):RecyclerView.Adapter<ReturnOrderVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReturnOrderVH {
        return ReturnOrderVH(LayoutInflater.from(parent.context).inflate(R.layout.adapter_return_orderslayout,parent,false))
    }

    override fun onBindViewHolder(holder: ReturnOrderVH, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ReturnOrderVH(itemView: View):RecyclerView.ViewHolder(itemView) {

}
