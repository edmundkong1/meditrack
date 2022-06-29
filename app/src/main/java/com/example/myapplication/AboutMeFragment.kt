package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ms.square.android.expandabletextview.ExpandableTextView
import kotlinx.android.synthetic.main.fragment_about_me.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutMeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutMeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var medicationsList = ArrayList<Medications>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        tv_medications.text = "yes"
//        val expTv: ExpandableTextView? =
//        expand_text_view.text = "yuhh"

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val medicationsAdapter = MedicationsAdapter(medicationsList)
        recycler_view.adapter = medicationsAdapter
        recycler_view.setHasFixedSize(true)
    }

    private fun initData() {
        medicationsList.add(Medications(
            "Norvasc",
            "40 mg",
            "Call Pharmacy: Refill required",
            "Take on empty stomach first thing in the morning, 1 hour before eating"
        ))

        medicationsList.add(Medications(
            "Brilinta",
            "10 mg",
            "N/A",
            "Take with dinner"
        ))

        medicationsList.add(Medications(
            "Libitor",
            "25 mg",
            "N/A",
            "Take an hour before lunch"
        ))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutMeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AboutMeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}