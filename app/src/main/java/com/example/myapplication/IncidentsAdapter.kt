package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener


class IncidentsAdapter(val symptomsList: List<String>) :
    RecyclerView.Adapter<IncidentsAdapter.ViewHolder>(){
    private var listener: OnToggleButtonClickListener? = null
    var symptoms: ArrayList<List<String>> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Spinner: Spinner
        var toggleButton: MaterialButtonToggleGroup

        //Link up the Main-List items layout components with their respective id
        init {
            Spinner = itemView.findViewById(R.id.input_tbl_attribute)
            toggleButton = itemView.findViewById(R.id.togglebtns)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.input_tbl_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        symptomsList[position]

        // below code makes it so that you don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            val spinner: Spinner = holder.Spinner
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                context,
                R.array.symptoms_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }

            holder.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
                if (isChecked) {
                    val text: String = holder.Spinner.selectedItem.toString()
                    val btn: MaterialButton = holder.toggleButton.findViewById(checkedId)
                    val rating: String = btn.text.toString()
                    symptoms.add(listOf(text, rating))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return symptomsList.size
    }

    interface OnToggleButtonClickListener {
    }


}