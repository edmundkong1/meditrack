package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.practitioners.InsuredPractitioner

import kotlinx.android.synthetic.main.fragment_practitioners.*

class PractitionersFragment : Fragment() {
    private var publicCoveredList = ArrayList<PublicPractitioners>()
    private var insuranceProvidersList = ArrayList<InsuranceProvider>()

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

        val insuranceProvider = PractitionersAdapter(insuranceProvidersList)
        rv_insurance_providers.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        rv_insurance_providers.adapter = insuranceProvider
        rv_insurance_providers.setHasFixedSize(true)

        val publicCovered= PractitionersAdapter(publicCoveredList)
        rv_practitioners_public.adapter = publicCovered
        rv_practitioners_public.setHasFixedSize(true)
    }

    private fun initData() {


        publicCoveredList.add(
            PublicPractitioners(
            "Dr. Eyes",
                "Optometrist",
            2022,
            9,
            30
            ))

        publicCoveredList.add(PublicPractitioners(
            "Dr. Depressed",
            "Therapist",
            2022,
            10,
            20
        ))

//        insuranceProvidersList.add(
//            InsuranceProvider(
//            "Sunlife",
//            "Dr. Bean, Dr. Olive, Dr. Letttuce"
//        ))
//
//        insuranceProvidersList.add(
//            InsuranceProvider(
//                "Manulife",
//                "40 mg",
//                "Refill required---------- DO OTHIS and don't look back on it yuh",
//                "Directions: Take daily at 9:00am (on empty stomach)."
//            ))
//        insuranceProvidersList.add(
//            InsuranceProvider(
//                "OHIP",
//                "40 mg",
//                "Refill required---------- DO OTHIS and don't look back on it yuh",
//                "Directions: Take daily at 9:00am (on empty stomach)."
//            ))
//
//        insuranceProvidersList.add(
//            InsuranceProvider(
//                "CanadaLife",
//                "40 mg",
//                "Refill required---------- DO OTHIS and don't look back on it yuh",
//                "Directions: Take daily at 9:00am (on empty stomach)."
//            ))
    }

}