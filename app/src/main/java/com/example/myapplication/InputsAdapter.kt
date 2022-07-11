package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.input_row.view.*

class InputsAdapter(val inputList: List<Inputs>) :
    RecyclerView.Adapter<InputsAdapter.InputsViewHolder>(){

    class InputsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputsViewHolder {
        return InputsViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.input_row, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InputsViewHolder, position: Int) {
        val currInput: Inputs = inputList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            tv_input_title.text = currInput.title
            tv_input_instructions.text = currInput.instructions
            tv_input_heading1.text = currInput.heading1
            tv_input_heading2.text = currInput.heading2

            val isExpandable : Boolean = currInput.expandable
            hidden_view.visibility = if (isExpandable) View.GONE else View.VISIBLE

            constraint_view_input.setOnClickListener {
                // toggle the box being expandable
                currInput.expandable = !currInput.expandable
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return inputList.size
    }
}