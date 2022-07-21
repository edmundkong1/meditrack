package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup


class IncidentsAdapter(val symptomsList: List<String>) :
    RecyclerView.Adapter<IncidentsAdapter.ViewHolder>(){
    private var listener: OnToggleButtonClickListener? = null

    //class InputsViewHolder(itemView: View) : RecyclerView.ViewHolder()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Spinner: Spinner
        var toggleButton: MaterialButtonToggleGroup

        // setText in Main-List title text
//        fun setData(name: String?) {
//            Spinner.text = name
//        }

        //Link up the Main-List items layout
        // components with their respective id
        init {
            Spinner = itemView.findViewById(R.id.input_tbl_attribute)
            toggleButton = itemView.findViewById(R.id.input_tbl_rating)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.input_tbl_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {val currInput: String = symptomsList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {
            //TODO: switch these from strings to Spinners(https://developer.android.com/guide/topics/ui/controls/spinner)
            //input_tbl_attribute.text = currInput
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

//            test()
//            input_tbl_attribute.ToggleBtns

//            for (i in input_tbl_attribute.ToggleBtns)
//            input_tbl_attribute.ToggleBtns
//
//            toggle.check(input_tbl_attribute.ToggleBtns.Btn1.id)
//            println(toggle.checkedButtonId)
//            { _, isChecked ->
//                // toggle the box being expandable
//                currInput.expandable = !currInput.expandable
//                test()
//                if (input_tbl_attribute.ToggleBtns.Btn1) {
//                    // The toggle is enabled
//                } else {
//                    // The toggle is disabled
//                }
            // Log.i("button test","testing")
//            val rating = holder.itemView.findViewById<MaterialButtonToggleGroup>(R.id.input_tbl_rating)//.findViewById(R.id.input_tbl_rating) as MaterialButtonToggleGroup
//            rating.addOnButtonCheckedListener { buttonGroup, checkedId, isChecked ->
//                Log.i("button test","testing2")
////                val mySpinner = findViewById<Spinner>(R.id.input_tbl_attribute)
////                val text = mySpinner.selectedItem.toString()
////                Log.i("button test",text)
//                if (isChecked) {
//                    when (checkedId) {
//                        //STORE CLICKED BUTTON
//                        //val buttonId = buttonGroup.getCheckedButtonId()
//                    }
//                } else {
//
//                }
//            }
            holder.toggleButton.setOnClickListener {
                // toggle the box being expandable
//                currInput.expandable = !currInput.expandable
//                notifyItemChanged(position)
                Log.i("button test","testing2")
                val text = holder.Spinner.selectedItem.toString()
                Log.i("button test",text)
            }
            holder.toggleButton.setOnClickListener(View.OnClickListener {
                if (listener != null) {
                    //listener.onToggleButtonClick(modelItems)
                }
            })

//                notifyItemChanged(position)
        }
    }

//    override fun onBindViewHolder(holder: InputsViewHolder, position: Int) {
//
//    }

    override fun getItemCount(): Int {
        return symptomsList.size
    }

    interface OnToggleButtonClickListener {
        fun onToggleButtonClick(toggleButton: MaterialButtonToggleGroup?)
    }

    fun setWhenClickListener(listener: OnToggleButtonClickListener?) {
        this.listener = listener
    }

}