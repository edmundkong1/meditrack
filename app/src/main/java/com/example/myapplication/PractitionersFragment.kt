package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about_me.*

import kotlinx.android.synthetic.main.fragment_practitioners.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PractitionersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PractitionersFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
    private var publicCoveredList = ArrayList<PublicCovered>()
//    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

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
        setRecyclerView()
    }


    private fun setRecyclerView() {
        val publicCoveredAdapter = PublicCoveredAdapter(publicCoveredList)
        recycler_view.adapter = publicCoveredAdapter
        recycler_view.setHasFixedSize(true)
    }

    private fun initData() {
        publicCoveredList.add(PublicCovered(
            "Norvasc",
            "40 mg",
            "Refill required---------- DO OTHIS and don't look back on it yuh",
            "Directions: Take daily at 9:00am (on empty stomach)."
        ))

        publicCoveredList.add(PublicCovered(
            "Brilinta",
            "10 mg",
            "New prescription required",
            "Directions: Take twice a week at 11:00am"
        ))}

}