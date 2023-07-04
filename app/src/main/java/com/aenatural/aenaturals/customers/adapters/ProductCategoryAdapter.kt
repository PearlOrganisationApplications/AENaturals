package com.aenatural.aenaturals.customers.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Category
import com.aenatural.aenaturals.apiservices.datamodels.GetCategoriesDM
import com.aenatural.aenaturals.baseframework.Session
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class ProductCategoryAdapter(val context: Context,var data: List<Category>,var imageEndpoint: String, private val callback: AdapterCallback) :
    RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryViewHolder>() {
     var session: Session = Session(context)
    private var adapterCallback: AdapterCallback? = null
    init {
        adapterCallback = callback
    }

    interface AdapterCallback {
        fun onItemClicked(categoryID: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryViewHolder {
        return ProductCategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.productcategorydesign, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {
        val category = data[position]
        val imageUrl = "$imageEndpoint${category.image}"

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.cat_image)
        holder.skincare.setOnClickListener {
            val catID = category.cat_id
//            Toast.makeText(context, category.cat_id, Toast.LENGTH_SHORT).show()
            session.setcategoryId(catID)
            adapterCallback?.onItemClicked(catID)

        }
        holder.cat_name.text =   category.cat_name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ProductCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cat_image = itemView.findViewById<ImageView>(R.id.cat_image)
        var cat_name = itemView.findViewById<TextView>(R.id.cat_name)
        var skincare = itemView.findViewById<LinearLayout>(R.id.skincare)

//        init {
//            skincare.setOnClickListener {
//                var category = data[position]
//                val categoryId = category.cat_id
//
//            }
//        }
    }

}





