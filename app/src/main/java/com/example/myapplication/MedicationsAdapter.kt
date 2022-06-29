package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MedicationsAdapter(val medicationList: List<Medications>) :
    RecyclerView.Adapter<MedicationsAdapter.MedicationViewHolder>(){

    class MedicationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        return MedicationViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.row, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val currMedication: Medications = medicationList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_name.text = currMedication.name
            tv_dosage.text = currMedication.dosage
            tv_actions.text = currMedication.actions
            tv_directions.text = currMedication.directions

            val isExpandable : Boolean = currMedication.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.VISIBLE else View.GONE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currMedication.expandable = !currMedication.expandable
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return medicationList.size
    }
}