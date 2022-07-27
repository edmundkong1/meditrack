package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_about_me.*
import java.io.FileInputStream
import java.io.ObjectInputStream
import kotlin.collections.ArrayList

class AboutMeFragment : Fragment() {

    private var medicationsList = ArrayList<AboutMeMeds>()
    private var conditionsList = ArrayList<Conditions>()
    private var symptomList = ArrayList<IncidentAboutMe>()
    private var appointmentList = ArrayList<Appointments>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onResume() {
        super.onResume()
        initData()
        setRecyclerView()
        initData2()
        setRecyclerView2()
        initData3()
        setRecyclerView3()
        initData4()
        setRecyclerView4()
    }

    // setup the recycler view
    private fun setRecyclerView() {
        val medicationsAdapter = AboutMeAdapter(medicationsList)
        rv_medications.adapter = medicationsAdapter
    }

    private fun setRecyclerView2() {
        val conditionsAdapter = AboutMeAdapter(conditionsList)
        rv_conditions.adapter = conditionsAdapter
    }

    private fun setRecyclerView3() {
        val symptomsAdapter = AboutMeAdapter(symptomList)
        recycler_view3.adapter = symptomsAdapter
    }

    private fun setRecyclerView4() {
        val appointmentAdapter = AboutMeAdapter(appointmentList)
        recycler_view4.adapter = appointmentAdapter
    }

    // initialize the recycler view with (temporary) mock data corresponding to mock data in calendar
    private fun initData() {
        val fis = FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempmedicationsList: Array<Array<Meds>> =
            ois.readObject() as Array<Array<Meds>>

        medicationsList.clear()
        //go through medications
        for(i in tempmedicationsList.indices){
            for(j in tempmedicationsList[i].indices){
                var newmed = true
                for(k in medicationsList.indices){
                    if(tempmedicationsList[i][j].name == medicationsList[k].name){
                        //check days of week
                        if(i == 0){
                            medicationsList[k].days += " Monday"
                        }
                        else if(i == 1){
                            medicationsList[k].days += " Tuesday"
                        }
                        else if(i == 2){
                            medicationsList[k].days += " Wednesday"
                        }
                        else if(i == 3){
                            medicationsList[k].days += " Thursday"
                        }
                        else if(i == 4){
                            medicationsList[k].days += " Friday"
                        }
                        else if(i == 5){
                            medicationsList[k].days += " Saturday"
                        }
                        else if(i == 6){
                            medicationsList[k].days += " Sunday"
                        }
                        newmed = false
                        break
                    }
                }
                if(newmed){
                    var days = ""
                    if(i == 0){
                        days = " Monday"
                    }
                    else if(i == 1){
                        days = " Tuesday"
                    }
                    else if(i == 2){
                        days = " Wednesday"
                    }
                    else if(i == 3){
                        days = " Thursday"
                    }
                    else if(i == 4){
                        days = " Friday"
                    }
                    else if(i == 5){
                        days = " Saturday"
                    }
                    else if(i == 6){
                        days = " Sunday"
                    }
                    medicationsList.add(AboutMeMeds(tempmedicationsList[i][j].name,
                        tempmedicationsList[i][j].timeHour!!,
                        tempmedicationsList[i][j].timeMin!!,
                        tempmedicationsList[i][j].dosage,
                        tempmedicationsList[i][j].actions!!,
                        tempmedicationsList[i][j].directions!!,
                        tempmedicationsList[i][j].totalAmount,
                        days))
                }
            }
        }
    }

    //init data for conditions
    private fun initData2() {
        val fis = FileInputStream(activity?.filesDir.toString() + "conditions_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempconditionsList: Array<Conditions> =
            ois.readObject() as Array<Conditions>

        conditionsList.clear()
        conditionsList.addAll(tempconditionsList)
    }

    //init data for incidents
    private fun initData3() {
        val fis = FileInputStream(activity?.filesDir.toString() + "incident_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val incidentsList: Array<Incident> =
            ois.readObject() as Array<Incident>

        val incidents: ArrayList<Incident> = incidentsList.toMutableList() as ArrayList<Incident>
        //medication taken per week
        symptomList.clear()
        for (incident in incidents) {
            symptomList.add(IncidentAboutMe(
                incident.symptom,
                incident.date,
                incident.severity))

        }
        ois.close()
    }

    //init data for appts
    private fun initData4() {
        val fis = FileInputStream(activity?.filesDir.toString() + "appointments_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val appointmentsList: Array<Appointments> =
            ois.readObject() as Array<Appointments>

        appointmentList.clear()
        appointmentList.addAll(appointmentsList)
    }
}