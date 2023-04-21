package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SliderModel
import com.aenatural.aenaturals.salesmans.AllProductsViewHolder
import com.pearl.aenaturals.R
import com.squareup.picasso.Picasso

class SliderAdapter (var data:List<SliderModel>): RecyclerView.Adapter<SliderViewholder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewholder {
        return SliderViewholder(LayoutInflater.from(parent.context).inflate(R.layout.row_slider,parent,false))

    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
        Picasso.get().load("https://cdn-ilcil.nitrocdn.com/gFzlebLlQaMswBWjmNqzvqWfTwaIjVkl/assets/images/optimized/rev-8524c2e/wp-content/uploads/2023/03/3-1.webp").into(holder.imageview)
      // holder.imageview?.setImageResource(data.get(position).image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class SliderViewholder (itemView: View): RecyclerView.ViewHolder(itemView){
    var imageview = itemView.findViewById<ImageView>(R.id.imageview)
}
