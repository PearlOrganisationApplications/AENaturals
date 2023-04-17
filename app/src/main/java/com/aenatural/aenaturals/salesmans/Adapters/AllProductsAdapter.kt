package com.aenatural.aenaturals.salesmans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class AllProductsAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<AllProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsViewHolder {
        return AllProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterallproductsdesign,parent,false))
    }

    override fun onBindViewHolder(holder: AllProductsViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class AllProductsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}