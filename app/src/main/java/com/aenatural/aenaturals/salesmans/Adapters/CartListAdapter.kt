package com.aenatural.aenaturals.salesmans.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.CustomerProduct
import com.aenatural.aenaturals.apiservices.datamodels.CustomerProductListDM
import org.json.JSONObject
import org.w3c.dom.Text

class CartListAdapter(var data:List<CustomerProduct>,private val itemList:MutableList<JSONObject>):RecyclerView.Adapter<CartListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        return CartListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adaptercartitems,parent,false))
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        var item = data[position]
        var quantity:Int = 0
        var productId = item.prod_id

        holder.itemName.text = item.prod_name
        holder.cart_itemPrice.text = item.pro_price
        holder.cart_itemDescription.text = item.prod_description


        holder.minusitemcart.setOnClickListener {
            if(quantity>0)
                quantity-=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()

            val selectedItem = itemList.find { it.getString("id") == productId }
            selectedItem?.put("quantity", quantity)
        }
        holder.additemcart.setOnClickListener {
            quantity+=1
            holder.itemquantityTV.text="quantity : "+quantity.toString()

            val selectedItem = itemList.find { it.getString("id") == productId }
            selectedItem?.put("quantity", quantity)
        }

        holder.selectCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
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
    }

    override fun getItemCount(): Int {
        return  data.size
    }

}
class CartListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var minusitemcart = itemView.findViewById<ImageView>(R.id.minusitemcart)
    var additemcart = itemView.findViewById<ImageView>(R.id.additemcart)
    var itemquantityTV = itemView.findViewById<TextView>(R.id.cartitemquantity)

    var itemName = itemView.findViewById<TextView>(R.id.cart_itemName)
    var cart_itemDescription = itemView.findViewById<TextView>(R.id.cart_itemDescription)
    var cart_itemPrice = itemView.findViewById<TextView>(R.id.cart_itemPrice)
    var selectCheckBox = itemView.findViewById<CheckBox>(R.id.selectCheckBox)
}