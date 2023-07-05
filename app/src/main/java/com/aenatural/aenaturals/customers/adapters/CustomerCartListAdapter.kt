package com.aenatural.aenaturals.customers.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.CartItem
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.bumptech.glide.Glide

class CustomerCartListAdapter(var data:ArrayList<CartItem>, var imageEndpoint: String):
    RecyclerView.Adapter<CustomerCartListAdapter.CartListViewHolder>() {

    private val selectedItem = HashMap<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        return CartListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptercartitems,parent,false))
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val item = data[position]
        var quantity:Int = 0
        holder.minusitemcart.setOnClickListener {
            if(quantity>0)
                quantity-=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()
        }
        holder.additemcart.setOnClickListener {
            quantity+=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()
        }
        holder.cart_itemName.text = item.prod_name
        holder.cart_itemDescription.text = item.prod_description
        holder.cart_itemPrice.text = item.pro_price

        if (!(item.image == null || item.image == "null")){
            var imageUrl = "$imageEndpoint${item.image}"

            Glide.with(holder.cart_imageView.context)
                .load(imageUrl)
                .into(holder.cart_imageView)
        }
        holder.selectCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedItem[item.prod_id] = item.quantity
                Log.d("ifselectedItem",selectedItem.toString())
            }else{
                selectedItem.remove(item.prod_id)
                Log.d("elseselectedItem",selectedItem.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }
    inner class CartListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var minusitemcart = itemView.findViewById<ImageView>(R.id.minusitemcart)
        var additemcart = itemView.findViewById<ImageView>(R.id.additemcart)
        var itemquantityTV = itemView.findViewById<TextView>(R.id.cartitemquantity)
        var cart_itemName = itemView.findViewById<TextView>(R.id.cart_itemName)
        var cart_itemDescription = itemView.findViewById<TextView>(R.id.cart_itemDescription)
        var cart_itemPrice = itemView.findViewById<TextView>(R.id.cart_itemPrice)
        var cart_imageView = itemView.findViewById<ImageView>(R.id.cart_imageView)
        var selectCheckBox = itemView.findViewById<CheckBox>(R.id.selectCheckBox)
    }
}
