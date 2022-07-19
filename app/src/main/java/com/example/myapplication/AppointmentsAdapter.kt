package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander.view.*

class AppointmentsAdapter(val appointmentList: List<Appointments>) :
    RecyclerView.Adapter<AppointmentsAdapter.AppointmentViewHolder>(){

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val currAppointment: Appointments = appointmentList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_title.text = currAppointment.name
            tv_prop1.text = currAppointment.dosage
            tv_prop2.text = currAppointment.actions
            tv_prop3.text = currAppointment.directions

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