package com.aenatural.aenaturals.distributors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel

class SellerAdapter(var data:List<SellerDataModel>) :RecyclerView.Adapter<SellerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        return SellerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptersellerdesign,parent,false))
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {

//        val dalaList = data[position]

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class SellerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

}
