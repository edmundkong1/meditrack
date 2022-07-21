package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about_me.*

// TODO:
//    use real data
//    add practitoners section
//    add scrolling
//    fix last section opening properly (don't default the sections being open)
//    add button to call pharmacy/doctors + other shortcuts
//    fix text overlap, go to next line
//    button to learn more about the medication

class AboutMeFragment : Fragment() {

    private var medicationsList = ArrayList<Medications>()
    private var conditionsList = ArrayList<Conditions>()
    private var symptomList = ArrayList<Symptoms>()
    private var appointmentList = ArrayList<Appointment>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val medicationsAdapter = MedicationsAdapter(medicationsList)
        recycler_view.adapter = medicationsAdapter
        recycler_view.setHasFixedSize(true)
    }

    private fun setRecyclerView2() {
        val conditionsAdapter = ConditionsAdapter(conditionsList)
        recycler_view2.adapter = conditionsAdapter
        recycler_view2.setHasFixedSize(true)
    }

    private fun setRecyclerView3() {
        val symptomsAdapter = SymptomsAdapter(symptomList)
        recycler_view3.adapter = symptomsAdapter
        recycler_view3.setHasFixedSize(true)
    }

    private fun setRecyclerView4() {
        val appointmentAdapter = AppointmentsAdapter(appointmentList)
        recycler_view4.adapter = appointmentAdapter
        recycler_view4.setHasFixedSize(true)
    }

    // initialize the recycler view with (temporary) mock data corresponding to mock data in calendar
    private fun initData() {
        medicationsList.add(Medications(
            "Norvasc",
            "2",
            "20",
            "20mg",
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            "A lot"
        ))

        medicationsList.add(Medications(
            "Brilinta",
            "2",
            "20",
            "20mg",
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            "A lot"
        ))

        medicationsList.add(Medications(
            "Libitor",
            "2",
            "20",
            "20mg",
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            "A lot"
        ))

        medicationsList.add(Medications(
            "Warfarin",
            "2",
            "20",
            "20mg",
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            "A lot"
        ))
    }

    private fun initData2() {
        conditionsList.add(Conditions(
            "COPD",
            "None",
            "Screaming, Crying, Throwing Up",
        ))

        conditionsList.add(Conditions(
            "Acid Reflux",
            "Heart Surgery",
            "Coughing, Screaming, Crying, Throwing Up",
        ))
    }

    private fun initData3() {
        symptomList.add(Symptoms(
            "Heartburn",
            "blank",
            "",
            "Directions: Take once a week at 12:00pm"
        ))

        symptomList.add(Symptoms(
            "Chest Pain",
            "blank",
            "",
            "Directions: Take once a week at 12:00pm"
        ))

        symptomList.add(Symptoms(
            "Sore Throat",
            "blank",
            "",
            "Directions: Take once a week at 12:00pm"
        ))
    }

    private fun initData4() {
        appointmentList.add(Appointment(
            "Dentist Appointment",
            "2",
            "30",
            "2022",
            "August",
            "20",
            "Dr. Teeth",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        )
        )

        appointmentList.add(Appointment(
            "Chiropractor Appointment",
            "12",
            "00",
            "2022",
            "December",
            "12",
            "Dr. Bones",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        ))

        appointmentList.add(Appointment(
            "Allergist Appointment",
            "6",
            "20",
            "2022",
            "September",
            "19",
            "Dr. Peanut",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        ))

    }
}