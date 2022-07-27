package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.myapplication.practitioners.InsuredPractitionerInfo

import kotlinx.android.synthetic.main.fragment_practitioners.*
import java.io.FileInputStream
import java.io.ObjectInputStream

class PractitionersFragment : Fragment() {
    private var insuranceProviderList = ArrayList<InsuranceProvider>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practitioners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
//        setProviderRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        initData()
        setInsuranceProviderRV()


        // add to the adapter to display
//        val insuranceProviderAdapter = InsuranceProviderAdapter(mutableInsuranceProviderList as ArrayList<InsuranceProvider>)
//        rv_insurance_providers.adapter = insuranceProviderAdapter
//        rv_insurance_providers.setHasFixedSize(true)

    }


    private fun setInsuranceProviderRV() {

        val insuranceProviderAdapter = PractitionersAdapter(insuranceProviderList)
//        rv_insurance_providers.setLayoutManager(
//            LinearLayoutManager(
//                context,
//                LinearLayoutManager.HORIZONTAL,
//                false
//            )
//        )
        rv_insurance_providers.adapter = insuranceProviderAdapter
        rv_insurance_providers.setHasFixedSize(true)
    }

    private fun initData() {
        //get insurance providers
        val fis = FileInputStream(activity?.filesDir.toString() + "insurance_providers_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempInsuranceProviderList: Array<InsuranceProvider> =
            ois.readObject() as Array<InsuranceProvider>

        insuranceProviderList.clear()
        insuranceProviderList.addAll(tempInsuranceProviderList)
    }

}