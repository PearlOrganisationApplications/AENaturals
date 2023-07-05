package com.aenatural.aenaturals.customers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.CategoriesProduct
import com.bumptech.glide.Glide

class CustomerAllItemAdapter(var data:List<CategoriesProduct>, var imageEndpoint: String,val callBack: CustomerAdapterCallBack):
    RecyclerView.Adapter<CustomerAllItemAdapter.CustomerAllItemHolder>() {

    fun updateData(newCategoryProduct: ArrayList<CategoriesProduct>, newImageEndpoint: String) {
        data = newCategoryProduct
        imageEndpoint = newImageEndpoint
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAllItemHolder {
        return CustomerAllItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.customer_allitem_design,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerAllItemHolder, position: Int) {
        val product = data[position]

if(!(product.cat_image==null||product.cat_image=="null")){
    val imageUrl = "$imageEndpoint${product.cat_image}"

    Glide.with(holder.itemView.context)
        .load(imageUrl)
        .into(holder.item_image)

}
        holder.item_name.text = product.prod_name
        holder.item_description.text = product.prod_description
        holder.item_price.text = product.prodPrice

        holder.imageView_addtocart.setOnClickListener {
            callBack.onCartIconClicked(product.prod_id)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CustomerAllItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var item_image = itemView.findViewById<ImageView>(R.id.item_image)
        var item_name = itemView.findViewById<TextView>(R.id.item_name)
        var item_description = itemView.findViewById<TextView>(R.id.item_description)
        var item_price = itemView.findViewById<TextView>(R.id.item_price)
        var imageView_addtocart = itemView.findViewById<ImageView>(R.id.imageView_addtocart)
    }

    interface CustomerAdapterCallBack {
        fun onCartIconClicked(categoryID: String)
    }
}
