package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander.view.*

class PublicCoveredAdapter(val publicCoveredList: List<PublicCovered>) :
    RecyclerView.Adapter<PublicCoveredAdapter.PublicCoveredViewHolder>(){

    class PublicCoveredViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicCoveredViewHolder {
        return PublicCoveredViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: PublicCoveredViewHolder, position: Int) {
        val currPublicCovered: PublicCovered = publicCoveredList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_title.text = currPublicCovered.name
            tv_prop1.text = currPublicCovered.dosage
            tv_prop2.text = currPublicCovered.actions
            tv_prop3.text = currPublicCovered.directions

            val isExpandable : Boolean = currPublicCovered.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

            linear_layout.setOnClickListener {
                // toggle the box being expandable
                currPublicCovered.expandable = !currPublicCovered.expandable
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return publicCoveredList.size
    }
}