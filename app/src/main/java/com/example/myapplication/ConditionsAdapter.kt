package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander_conditions.view.*

class ConditionsAdapter(val conditionsList: List<Conditions>) :
    RecyclerView.Adapter<ConditionsAdapter.ConditionsViewHolder>(){

    class ConditionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConditionsViewHolder {
        return ConditionsViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander_conditions, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: ConditionsViewHolder, position: Int) {
        val currCondition: Conditions = conditionsList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_title.text = currCondition.name
            tv_prop1.text = currCondition.relatedProceduresHistory
            tv_prop2.text = currCondition.symptomsHistory

            val isExpandable : Boolean = currCondition.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currCondition.expandable = !currCondition.expandable
                notifyItemChanged(position)
            }
        }

    }
    override fun getItemCount(): Int {
        return conditionsList.size
    }
}