package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ListView
import com.google.android.material.button.MaterialButtonToggleGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var ButtonViews: ArrayList<MaterialButtonToggleGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creating a list of items with custom adapter
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButtonViews.add(view.findViewById(R.id.HeartburnBtns))
        ButtonViews.add(view.findViewById(R.id.ChestPainBtns))
        ButtonViews.add(view.findViewById(R.id.SoreThroatBtns))
        ButtonViews.add(view.findViewById(R.id.BreathBtns))
        ButtonViews.add(view.findViewById(R.id.WheezingBtns))
        ButtonViews.add(view.findViewById(R.id.NauseaBtns))
        ButtonViews.add(view.findViewById(R.id.OtherBtns))
        for(btn in ButtonViews){
            btn.setSelectionRequired(true)
            btn.addOnButtonCheckedListener { buttonGroup, checkedId, isChecked ->
                if(isChecked){
                    when(checkedId){
                        //STORE CLICKED BUTTON
                        val buttonId = buttonGroup.getCheckedButtonId()
                    }
                }

            }

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InputFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}