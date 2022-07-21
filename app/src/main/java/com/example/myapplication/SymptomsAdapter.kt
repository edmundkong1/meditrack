package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander.view.*

class SymptomsAdapter(val symptomList: List<Symptoms>) :
    RecyclerView.Adapter<SymptomsAdapter.SymptomViewHolder>(){

    class SymptomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        return SymptomViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        val currSymptom: Symptoms = symptomList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_title.text = currSymptom.name
            tv_prop1.text = currSymptom.dosage
            tv_prop2.text = currSymptom.actions
            tv_prop3.text = currSymptom.directions

            val isExpandable : Boolean = currSymptom.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currSymptom.expandable = !currSymptom.expandable
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return symptomList.size
    }
}