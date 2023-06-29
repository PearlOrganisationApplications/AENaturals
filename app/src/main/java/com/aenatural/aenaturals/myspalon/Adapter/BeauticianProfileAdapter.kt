package com.aenatural.aenaturals.myspalon.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Staff

class BeauticianProfileAdapter(private val staffList: List<Staff>) :
    RecyclerView.Adapter<BeauticianProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val mobileNumber: TextView = view.findViewById(R.id.mobileNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beautician_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val staff = staffList[position]
        holder.nameTextView.text = staff.fullName
        holder.mobileNumber.text = staff.mobile
    }

    override fun getItemCount(): Int {
       return staffList.size
    }
}