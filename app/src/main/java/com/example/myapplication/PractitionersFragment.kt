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
    private var practitionerList = ArrayList<Practitioner>()

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
    }

    override fun onResume() {
        super.onResume()
        initData()
        setInsuranceProviderRV()
    }

    private fun setInsuranceProviderRV() {
        val practitionerAdapter = PractitionersAdapter(practitionerList)
        rv_practitioners.adapter = practitionerAdapter
    }

    private fun initData() {
        //get insurance providers
        val fis = FileInputStream(activity?.filesDir.toString() + "practitioners_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempPractitionerList: Array<Practitioner> =
            ois.readObject() as Array<Practitioner>

        practitionerList.clear()
        practitionerList.addAll(tempPractitionerList)
    }

}