package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.fragment_practitioners.*

class PractitionersFragment : Fragment() {
    private var publicCoveredList = ArrayList<PublicCovered>()
    private var insuranceProvidersList = ArrayList<InsuranceProviders>()

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
        setRecyclerViews()
    }


    private fun setRecyclerViews() {

        val insuranceProvidersAdapter = InsuranceProvidersAdapter(insuranceProvidersList)
        rv_insurance_providers.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rv_insurance_providers.adapter = insuranceProvidersAdapter
//        rv_insurance_providers.setHasFixedSize(true)

        val publicCoveredAdapter = PublicCoveredAdapter(publicCoveredList)
        rv_practitioners_private.adapter = publicCoveredAdapter
        rv_practitioners_private.setHasFixedSize(true)
    }

    private fun initData() {
        publicCoveredList.add(PublicCovered(
            "Optometrist",
            "40 mg",
            "Refill required---------- DO OTHIS and don't look back on it yuh",
            "Directions: Take daily at 9:00am (on empty stomach)."
        ))

        publicCoveredList.add(PublicCovered(
            "Therapist",
            "10 mg",
            "New prescription required",
            "Directions: Take twice a week at 11:00am"
        ))

        insuranceProvidersList.add(
            InsuranceProviders(
            "Sunlife",
            "40 mg",
            "Refill required---------- DO OTHIS and don't look back on it yuh",
            "Directions: Take daily at 9:00am (on empty stomach)."
        ))

        insuranceProvidersList.add(
            InsuranceProviders(
                "Manulife",
                "40 mg",
                "Refill required---------- DO OTHIS and don't look back on it yuh",
                "Directions: Take daily at 9:00am (on empty stomach)."
            ))
        insuranceProvidersList.add(
            InsuranceProviders(
                "OHIP",
                "40 mg",
                "Refill required---------- DO OTHIS and don't look back on it yuh",
                "Directions: Take daily at 9:00am (on empty stomach)."
            ))

        insuranceProvidersList.add(
            InsuranceProviders(
                "CanadaLife",
                "40 mg",
                "Refill required---------- DO OTHIS and don't look back on it yuh",
                "Directions: Take daily at 9:00am (on empty stomach)."
            ))
    }

}