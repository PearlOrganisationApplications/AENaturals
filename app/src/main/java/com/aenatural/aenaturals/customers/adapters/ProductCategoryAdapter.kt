package com.aenatural.aenaturals.customers.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.GetCategoriesDM
import org.w3c.dom.Text

class ProductCategoryAdapter(var data: List<GetCategoriesDM>) :
    RecyclerView.Adapter<ProductCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryViewHolder {
        return ProductCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.productcategorydesign, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ProductCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var cat_image = itemView.findViewById<ImageView>(R.id.cat_image)
    var cat_name = itemView.findViewById<TextView>(R.id.cat_name)
}