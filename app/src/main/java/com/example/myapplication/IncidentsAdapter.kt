package com.example.myapplication

import android.hardware.biometrics.BiometricManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.input_row.view.*
import kotlinx.android.synthetic.main.input_tbl_row.view.*

class IncidentsAdapter(val symptomsList: List<String>) :
    RecyclerView.Adapter<IncidentsAdapter.InputsViewHolder>(){

    class InputsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputsViewHolder {
        return InputsViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.input_tbl_row, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InputsViewHolder, position: Int) {
        val currInput: String = symptomsList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            //TODO: switch these from strings to Spinners(https://developer.android.com/guide/topics/ui/controls/spinner)
            input_tbl_attribute.text = currInput




            input_tbl_rating.setOnClickListener {
                // toggle the box being expandable
//                currInput.expandable = !currInput.expandable
//                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return symptomsList.size
    }
}