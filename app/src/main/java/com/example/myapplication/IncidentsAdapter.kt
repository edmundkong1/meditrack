package com.example.myapplication

import android.hardware.biometrics.BiometricManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.input_row.view.*
import kotlinx.android.synthetic.main.input_tbl_row.view.*
import kotlinx.android.synthetic.main.toggle_button.view.*

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

//            test()
//            input_tbl_attribute.ToggleBtns

//            for (i in input_tbl_attribute.ToggleBtns)
//            input_tbl_attribute.ToggleBtns
//
//            toggle.check(input_tbl_attribute.ToggleBtns.Btn1.id)
//            println(toggle.checkedButtonId)
//            { _, isChecked ->
//                // toggle the box being expandable
////                currInput.expandable = !currInput.expandable
////                test()
//                if (input_tbl_attribute.ToggleBtns.Btn1) {
//                    // The toggle is enabled
//                } else {
//                    // The toggle is disabled
//                }
            input_tbl_rating.ToggleBtns.
            setOnClickListener {
                // toggle the box being expandable
//                currInput.expandable = !currInput.expandable
//                notifyItemChanged(position)
                println("testttttttt")
            }

//                notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return symptomsList.size
    }


    // setup the recycler view
    private fun test() {
        println("test")
    }
}