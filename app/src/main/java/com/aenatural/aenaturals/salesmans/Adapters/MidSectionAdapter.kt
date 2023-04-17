package com.aenatural.aenaturals.salesmans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class MidSectionAdapter(var data:List<RetailerDataModel>):RecyclerView.Adapter<MidSectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MidSectionViewHolder {
        return MidSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptermidsectiondesign,parent,false))
    }

    override fun onBindViewHolder(holder: MidSectionViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class MidSectionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}