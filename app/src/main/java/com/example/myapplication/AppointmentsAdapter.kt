package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander_appointments.view.*

class AppointmentsAdapter(val appointmentList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder>(){

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander_appointments, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val currAppointment: Appointment = appointmentList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_title.text = currAppointment.name
            tv_prop1.text = currAppointment.timeHour
            tv_prop2.text = currAppointment.timeMin
            tv_prop3.text = currAppointment.year
            tv_prop4.text = currAppointment.theMonth
            tv_prop5.text = currAppointment.day
            tv_prop6.text = currAppointment.doctor
            tv_prop7.text = currAppointment.phoneNumber
            tv_prop8.text = currAppointment.address

            val isExpandable : Boolean = currAppointment.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currAppointment.expandable = !currAppointment.expandable
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }
}