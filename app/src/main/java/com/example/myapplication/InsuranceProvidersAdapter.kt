package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.insurance_provider.view.*

class InsuranceProviderAdapter(val insuranceProviderList: ArrayList<InsuranceProvider>) :
    RecyclerView.Adapter<InsuranceProviderAdapter.InsuranceProviderViewHolder>() {

    class InsuranceProviderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceProviderViewHolder {
        return InsuranceProviderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate( // takes 4 arguments, all optional: resources, parser, resources, parser
                    R.layout.insurance_provider, // R is to access resources, layout is a resource
                    parent,
                    false // don't wanna attach this view to the root layout, set to false
                )
        )
    }

    override fun onBindViewHolder(holder: InsuranceProviderViewHolder, position: Int) {
        val currProvider: InsuranceProvider = insuranceProviderList[position]

        holder.itemView.apply {
            tv_provider_name.text = currProvider.name
        }
    }

    override fun getItemCount() = insuranceProviderList.size

}