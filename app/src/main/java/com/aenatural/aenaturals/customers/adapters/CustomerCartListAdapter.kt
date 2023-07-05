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
import org.json.JSONObject

class CustomerCartListAdapter(var data:ArrayList<CartItem>, var imageEndpoint: String,private val itemList:MutableList<JSONObject>):
    RecyclerView.Adapter<CustomerCartListAdapter.CartListViewHolder>() {

    private val selectedItem = HashMap<String, String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        return CartListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptercartitems,parent,false))
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        val item = data[position]
        var quantity:Int = 1
        val productId =item.prod_id
        holder.minusitemcart.setOnClickListener {
            if (quantity > 0) {
                quantity -= 1
            }
            holder.itemquantityTV.text = "Quantity: $quantity"
            val selectedItem = itemList.find { it.getString("id") == productId }
            selectedItem?.put("quantity", quantity)
        }

        holder.additemcart.setOnClickListener {
            quantity += 1
            holder.itemquantityTV.text = "Quantity: $quantity"
            Log.d("selectedItem1", selectedItem.toString())

            val selectedItem = itemList.find { it.getString("id") == productId }
            selectedItem?.put("quantity", quantity)
        }
        holder.cart_itemName.text = item.prod_name
        holder.cart_itemDescription.text = item.prod_description
        holder.cart_itemPrice.text = item.pro_price


        holder.selectCheckBox.setOnCheckedChangeListener { _, isChecked ->
                Log.d("Item",item.toString())
                if (isChecked) {
                    val itemJson = JSONObject()
                    itemJson.put("id", productId)
                    itemJson.put("quantity", quantity)

                    itemList.add(itemJson)
                } else {
                    val itemToRemove = itemList.find { it.getString("id") == productId }
                    itemList.remove(itemToRemove)
                }
        }
        if (!(item.image == null || item.image == "null")){
            var imageUrl = "$imageEndpoint${item.image}"

            Glide.with(holder.cart_imageView.context)
                .load(imageUrl)
                .into(holder.cart_imageView)
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
