package com.aenatural.aenaturals.myspalon.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Customer

class CustomerParlourListAdapter(private val customerList: List<Customer>):
    RecyclerView.Adapter<CustomerParlourListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val mobileNumber: TextView = view.findViewById(R.id.mobileNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beautician_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = customerList[position]
        holder.nameTextView.text = customer.full_name
        holder.mobileNumber.text = customer.mobile
    }

    override fun getItemCount(): Int {
       return customerList.size
    }
}