package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander_information.view.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class PractitionersAdapter(val practitionerList: ArrayList<Practitioner>) :
    RecyclerView.Adapter<PractitionersAdapter.InformationViewHolder>(){

    class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        return InformationViewHolder (
                LayoutInflater.from(parent.context).inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander_information, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        val practitioner: Practitioner = practitionerList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {

            tv_title.text = practitioner.name
            val textFields = practitioner.practitionersAdapter()
            val arrayAdapter = ArrayAdapter(context, R.layout.simple_expander_text, textFields.toTypedArray())
            information_list.adapter = arrayAdapter


            val isExpandable : Boolean = practitioner.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE
            linear_layout.setOnClickListener {
                // toggle the box being expandable
                practitioner.expandable = !practitioner.expandable
                notifyItemChanged(position)
            }

            //occurs when button gets clicked
            button.setOnClickListener {
                // toggle the box being expandable
                val fis1 = FileInputStream(context?.filesDir.toString() + "practitioners_list.meditrack")
                val ois1 = ObjectInputStream(fis1)
                var practitionerList: Array<Practitioner> =
                    ois1.readObject() as Array<Practitioner>

                ois1.close()
                fis1.close()

                //iterate through list of practitioners
                for (i in practitionerList.indices) {
                    if (practitionerList[i].name == practitioner.name) {
                        val tempPractitionersList = practitionerList.toMutableList()
                        tempPractitionersList.removeAt(i)
                        practitionerList = tempPractitionersList.toTypedArray()
                        break
                    }
                }

                val pracfos =
                    FileOutputStream(context?.filesDir.toString() + "practitioners_list.meditrack")
                val pracoos = ObjectOutputStream(pracfos)
                pracoos.writeObject(practitionerList)
                pracoos.close()

                linear_layout.visibility = View.GONE
            }

            val params = information_list.layoutParams
            params.height =  200 * textFields.size
            information_list.layoutParams = params
            information_list.requestLayout()

        }
    }

    override fun getItemCount(): Int {
        return practitionerList.size
    }
}