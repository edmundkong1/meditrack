package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_input.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment(R.layout.fragment_input) {
//    // TODO: Rename and change types of parameters
//    private lateinit var ButtonViews: ArrayList<MaterialButtonToggleGroup>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        //creating a list of items with custom adapter
//        val view = inflater.inflate(R.layout.fragment_input, container, false)
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        ButtonViews.add(view.findViewById(R.id.HeartburnBtns))
//        ButtonViews.add(view.findViewById(R.id.ChestPainBtns))
//        ButtonViews.add(view.findViewById(R.id.SoreThroatBtns))
//        ButtonViews.add(view.findViewById(R.id.BreathBtns))
//        ButtonViews.add(view.findViewById(R.id.ToggleBtns))
//        ButtonViews.add(view.findViewById(R.id.NauseaBtns))
//        ButtonViews.add(view.findViewById(R.id.OtherBtns))
    //TODO: Add Button Listners into INcidents Adapter using the following
//        for(btn in ButtonViews){
//            btn.setSelectionRequired(true)
//            btn.addOnButtonCheckedListener { buttonGroup, checkedId, isChecked ->
//                if(isChecked){
//                    when(checkedId){
//                        //STORE CLICKED BUTTON
//                        //val buttonId = buttonGroup.getCheckedButtonId()
//                    }
//                }
//
//            }
//
//        }
//
//    }
private var inputsList = ArrayList<Inputs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setRecyclerView()
    }

    // setup the recycler view
    private fun setRecyclerView() {
        val inputsAdapter = InputsAdapter(inputsList)
        rv_inputs.adapter = inputsAdapter
    }

    // initialize the recycler view with (temporary) mock data corresponding to mock data in calendar
    private fun initData() {

        inputsList.add(Inputs(
            "Enter Appointments",
            "When is your Appointment",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

        inputsList.add(Inputs(
            "Enter Prescription",
            "What is your new prescription?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))
        
        inputsList.add(Inputs(
            "Enter Incident Report",
            "What are your symptoms today?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

        inputsList.add(Inputs(
            "Enter Practitioners",
            "What is your practitioner's info?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))


        inputsList.add(Inputs(
            "Enter Insurance Info",
            "What is your insurance plan?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))


        inputsList.add(Inputs(
            "Enter Conditions",
            "What are your conditions?",
            "Symptom",
            "Severity",
            listOf("Symptom1","Symptom2")
        ))

    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AboutMeFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AboutMeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}