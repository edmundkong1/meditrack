package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.insurance_provider.view.*
import kotlinx.android.synthetic.main.simple_expander.view.*

class InsuranceProviderAdapter(val insuranceProviderList: ArrayList<InsuranceProvider>) :
    RecyclerView.Adapter<InsuranceProviderAdapter.InsuranceProviderViewHolder>() {

    class InsuranceProviderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceProviderViewHolder {
        return InsuranceProviderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.simple_expander, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InsuranceProviderViewHolder, position: Int) {
        val currProvider: InsuranceProvider = insuranceProviderList[position]

//        var insuredPractitionerName = currProvider.insuredPractitionerInfoList[0].title

//        var userPractitionerName = currProvider._userPractitionerList[0].title

        holder.itemView.apply {
            tv_title.text = currProvider.name
            tv_prop1.text = "Name, Title: " + currProvider.userPractitionerList[0].name + ", " + currProvider.userPractitionerList[0].title
            tv_prop2.text = "Cost Per Session:" + currProvider.userPractitionerList[0].costPerSession
            tv_prop3.text = "Total Coverage by Insurance:" + currProvider.insuredPractitionerInfoList[0].coveredAmount
        }
    }

    override fun getItemCount() = insuranceProviderList.size

}