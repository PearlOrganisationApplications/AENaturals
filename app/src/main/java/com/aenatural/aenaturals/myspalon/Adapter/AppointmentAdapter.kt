package com.aenatural.aenaturals.myspalon.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.datamodels.Appointment

class AppointmentAdapter(private val appointmentList: MutableList<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

//    private val appointmentList: MutableList<Appointment> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fullNameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val mobileTextView: TextView = itemView.findViewById(R.id.mobileNumber)
       /* private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val reasonTextView: TextView = itemView.findViewById(R.id.reasonTextView)
*/
        fun bind(appointment: Appointment) {
            fullNameTextView.text = appointment.added_by_user_id
           mobileTextView.text = appointment.app_reason
//            mobileTextView.text = appointment.customer_id
          /*  dateTextView.text = appointment.app_date
            timeTextView.text = appointment.app_time
            reasonTextView.text = appointment.app_reason*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
//        item_beautician_profile
            .inflate(R.layout.item_beautician_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.bind(appointment)
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    fun setData(    appointments: List<Appointment>) {
        appointmentList.clear()
        appointmentList.addAll(appointments)
        notifyDataSetChanged()
    }
}
