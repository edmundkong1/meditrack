package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about_me.*

//TODO:
//    use real data
//    add practitoners section
//    add scrolling
//    fix last section opening properly (don't default the sections being open)
//    add button to call pharmacy/doctors + other shortcuts
//    fix text overlap, go to next line
//    button to learn more about the medication

class AboutMeFragment : Fragment() {

    private var medicationsList = ArrayList<Medications>()

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
    }

    // setup the recycler view
    private fun setRecyclerView() {
        val medicationsAdapter = MedicationsAdapter(medicationsList)
        recycler_view.adapter = medicationsAdapter
        recycler_view.setHasFixedSize(true)
    }

    // initialize the recycler view with (temporary) mock data corresponding to mock data in calendar
    private fun initData() {
        medicationsList.add(Medications(
            "Norvasc",
            "40 mg",
            "Refill required",
            "Directions: Take daily at 9:00am (on empty stomach)."
        ))

        medicationsList.add(Medications(
            "Brilinta",
            "10 mg",
            "New prescription required",
            "Directions: Take twice a week at 11:00am"
        ))

        medicationsList.add(Medications(
            "Libitor",
            "25 mg",
            "",
            "Directions: Take four times a week at 3:15pm"
        ))

        medicationsList.add(Medications(
            "Warfarin",
            "100 mg",
            "",
            "Directions: Take once a week at 12:00pm"
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