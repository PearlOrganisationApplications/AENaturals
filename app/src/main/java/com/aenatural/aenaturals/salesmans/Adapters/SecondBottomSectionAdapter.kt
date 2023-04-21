package com.aenatural.aenaturals.salesmans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R

class SecondBottomSectionAdapter(var data:List<RetailerDataModel>): RecyclerView.Adapter<SecondBottomSectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondBottomSectionViewHolder {
        return SecondBottomSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptersecondbottomsection,parent,false))
    }

    override fun onBindViewHolder(holder: SecondBottomSectionViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class SecondBottomSectionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}