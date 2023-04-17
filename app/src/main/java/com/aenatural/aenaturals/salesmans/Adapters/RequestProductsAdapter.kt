package com.aenatural.aenaturals.salesmans.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.pearl.aenaturals.R

class RequestProductsAdapter(var data:ArrayList<RetailerDataModel>,var context:Context):RecyclerView.Adapter<RequestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapterrequestproductsalesman,parent,false))
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class RequestViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}