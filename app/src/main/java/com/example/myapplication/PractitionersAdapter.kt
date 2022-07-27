package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.simple_expander_information.view.*

class PractitionersAdapter(val insuranceProviderList: ArrayList<InsuranceProvider>) :
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
        val insuranceProvider: InsuranceProvider = insuranceProviderList[position]

        // below code makes it so that u don't need to prepend with holder.itemView each time you make a change
        holder.itemView.apply {

            tv_title.text = insuranceProvider.name
            val textFields = insuranceProvider.practitionersAdapter()
            val arrayAdapter = ArrayAdapter(context, R.layout.simple_expander_text, textFields.toTypedArray())
            information_list.adapter = arrayAdapter


            val isExpandable : Boolean = insuranceProvider.expandable
            rl_expandable_layout.visibility = if (isExpandable) View.GONE else View.VISIBLE

//            rl_expandable_layout.setLayoutManager(
//            LinearLayoutManager(
//                context,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )
//        )
            linear_layout.setOnClickListener {
                // toggle the box being expandable
                insuranceProvider.expandable = !insuranceProvider.expandable
                notifyItemChanged(position)
            }


            val params = information_list.layoutParams
            params.height =  200 * textFields.size
            information_list.layoutParams = params
            information_list.requestLayout()

        }

    }

    override fun getItemCount(): Int {
        return insuranceProviderList.size
    }
}