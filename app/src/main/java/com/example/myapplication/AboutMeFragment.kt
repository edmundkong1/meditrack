package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_about_me.*
import java.io.FileInputStream
import java.io.ObjectInputStream

// TODO:
//    use real data
//    add practitoners section
//    add scrolling
//    fix last section opening properly (don't default the sections being open)
//    add button to call pharmacy/doctors + other shortcuts
//    fix text overlap, go to next line
//    button to learn more about the medication

class AboutMeFragment : Fragment() {

    private var medicationsList = ArrayList<AboutMeMeds>()
    private var conditionsList = ArrayList<Conditions>()
    private var symptomList = ArrayList<Symptoms>()
    private var appointmentList = ArrayList<Appointments>()

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


        bttn_insert_med.setOnClickListener {
            val position: Int = et_insert_med.getText().toString().toInt()
//            insertMedicationItem(position)
        }

        bttn_remove_med.setOnClickListener {
            val position: Int = et_insert_med.getText().toString().toInt()
//            removeMedicationItem(position)
        }
    }

//    private fun insertMedicationItem(position: Int) {
//        medicationsList.add(
//            position,
//            Meds("new med", "New Item At Position$position", "This is Line 2", "This is Line 2")
//        )
//        AboutMeAdapter(medicationsList).notifyItemInserted(position)
//    }
//
//    fun removeMedicationItem(position: Int) {
//        medicationsList.removeAt(position)
//        AboutMeAdapter(medicationsList).notifyItemRemoved(position)
//    }

    // setup the recycler view
    private fun setRecyclerView() {
        val medicationsAdapter = AboutMeAdapter(medicationsList)
        rv_medications.adapter = medicationsAdapter
        rv_medications.setHasFixedSize(true)
    }

    private fun setRecyclerView2() {
        val conditionsAdapter = AboutMeAdapter(conditionsList)
        rv_conditions.adapter = conditionsAdapter
        rv_conditions.setHasFixedSize(true)
    }

    private fun setRecyclerView3() {
        val symptomsAdapter = AboutMeAdapter(symptomList)
        recycler_view3.adapter = symptomsAdapter
        recycler_view3.setHasFixedSize(true)
    }

    private fun setRecyclerView4() {
        val appointmentAdapter = AboutMeAdapter(appointmentList)
        recycler_view4.adapter = appointmentAdapter
        recycler_view4.setHasFixedSize(true)
    }

    // initialize the recycler view with (temporary) mock data corresponding to mock data in calendar
    private fun initData() {
        val fis = FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempmedicationsList: Array<Array<Meds>> =
            ois.readObject() as Array<Array<Meds>>

        for(i in tempmedicationsList.indices){
            for(j in tempmedicationsList[i].indices){
                var newmed = true
                for(k in medicationsList.indices){
                    if(tempmedicationsList[i][j].name == medicationsList[k].name){
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

       /* medicationsList.add(Meds(
            "Norvasc",
            2,
            20,
            20,
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            2000
        ))

        medicationsList.add(Meds(
            "Brilinta",
            2,
            20,
            20,
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            2000
        ))

        medicationsList.add(Meds(
            "Libitor",
            2,
            20,
            20,
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            2000
        ))

        medicationsList.add(Meds(
            "Warfarin",
            2,
            20,
            20,
            "none",
            "\"Refill required---------- DO OTHIS and don't look back on it yuh\"",
            2000
        ))

        */
    }

    private fun initData2() {
        val fis = FileInputStream(activity?.filesDir.toString() + "conditions_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val tempconditionsList: Array<Conditions> =
            ois.readObject() as Array<Conditions>

        conditionsList.addAll(tempconditionsList)

//        conditionsList.add(Conditions(
//            "COPD",
//            "None",
//            "Screaming, Crying, Throwing Up",
//        ))
//
//        conditionsList.add(Conditions(
//            "Acid Reflux",
//            "Heart Surgery",
//            "Coughing, Screaming, Crying, Throwing Up",
//        ))
    }

    private fun initData3() {
        symptomList.add(Symptoms(
            "Heartburn",
            0,
            "",
            "Directions: Take once a week at 12:00pm"
        ))

        symptomList.add(Symptoms(
            "Chest Pain",
            0,
            "",
            "Directions: Take once a week at 12:00pm"
        ))

        symptomList.add(Symptoms(
            "Sore Throat",
            0,
            "",
            "Directions: Take once a week at 12:00pm"
        ))
    }

    private fun initData4() {
        val fis = FileInputStream(activity?.filesDir.toString() + "appointments_list.meditrack")
        val ois = ObjectInputStream(fis)

        @Suppress("UNCHECKED_CAST")
        val appointmentsList: Array<Appointments> =
            ois.readObject() as Array<Appointments>

        appointmentList.addAll(appointmentsList)
        /*
        appointmentList.add(Appointments(
            "Dentist Appointment",
            2,
            30,
            2022,
            8,
            20,
            "Dr. Teeth",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        )
        )

        appointmentList.add(Appointments(
            "Chiropractor Appointment",
            12,
            0,
            2022,
            11,
            12,
            "Dr. Bones",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        ))

        appointmentList.add(Appointments(
            "Allergist Appointment",
            6,
            20,
            2022,
            8,
            19,
            "Dr. Peanut",
            "226-555-5555",
            "255 Sunview St. Waterloo, ON N2L 3V8"
        ))
         */

    }
}