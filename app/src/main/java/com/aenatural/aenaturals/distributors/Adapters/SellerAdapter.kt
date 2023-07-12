package com.aenatural.aenaturals.distributors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.SalesmanDetail
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.bumptech.glide.Glide

class SellerAdapter(var data: List<SalesmanDetail>, var endpoint: String) :
    RecyclerView.Adapter<SellerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        return SellerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adaptersellerdesign, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        var salesman = data[position]
        holder.name.text = salesman.username
        holder.email.text = salesman.email
        holder.number.text = salesman.contact

        if (salesman.image == "" || salesman.image == "null" || salesman.image == " ") {
            holder.image.setImageResource(R.drawable.ic_ticketseller)
        } else {
            val imageUrl = "$endpoint${salesman.image}"
            try {
                Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .into(holder.image)

            } catch (_: Exception) {

            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class SellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name = itemView.findViewById<TextView>(R.id.SL_name)
    var email = itemView.findViewById<TextView>(R.id.SL_email)
    var number = itemView.findViewById<TextView>(R.id.SL_mobile)
    var image = itemView.findViewById<ImageView>(R.id.SL_image)
}
