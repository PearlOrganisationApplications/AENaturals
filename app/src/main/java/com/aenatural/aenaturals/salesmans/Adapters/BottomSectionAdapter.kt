package com.aenatural.aenaturals.salesmans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class BottomSectionAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<BottomSectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSectionViewHolder {
        return BottomSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterbottomsectiondesign,parent,false))
    }

    override fun onBindViewHolder(holder: BottomSectionViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class BottomSectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}