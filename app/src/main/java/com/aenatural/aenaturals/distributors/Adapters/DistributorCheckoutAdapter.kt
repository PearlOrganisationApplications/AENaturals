package com.aenatural.aenaturals.salesmans.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class DistributorCheckoutAdapter(var data:ArrayList<RetailerDataModel>):RecyclerView.Adapter<CheckoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        return CheckoutViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dist_checkout_layout,parent,false))
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return  data.size
    }

}
class CheckoutViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

}