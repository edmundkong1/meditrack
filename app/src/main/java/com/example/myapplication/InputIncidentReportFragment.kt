package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.fragment_input_incident_report.*
import java.util.*


class InputIncidentReportFragment : Fragment() {
    private var inputsList = ArrayList<String>()

    private var incidentAdapter: IncidentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //incidentAdapter = IncidentsAdapter(inputsList)
        //rv_inputs.adapter = IncidentsAdapter(inputsList)
//        incidentAdapter = .setWhenClickListener(new IncidentsAdpater.OnItemsClickListener() {
//            override fun OnToggleButtonClick(spinnerValue: String) {
//                algorithmTitleText.setText(rvOneModel.getName())
//                setRVTwoList(rvOneModel.getNum())
//            }
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_incident_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData()
        setRecyclerView()
        val chooseDate: EditText = view.findViewById(R.id.incidentDate)
        chooseDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = this.context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { datePicker, year, month, day -> chooseDate.setText(day.toString() + "/" + (month + 1) + "/" + year)}, year, month, dayOfMonth
                )
            }
            if (datePickerDialog != null) {
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
        }
        val addRowBtn: Button = view.findViewById(R.id.add_row_btn)
        addRowBtn.setOnClickListener {
            val symptoms  = resources.getStringArray(R.array.symptoms_array)
            if(getSizeOfList() < symptoms.size) {
                inputsList.add("New Symptom");
                refreshRV()
            }else{
                addRowBtn.visibility = View.INVISIBLE
            }
        }
        val submitIncidents: Button = view.findViewById(R.id.submitInputIncident)
        submitIncidents.setOnClickListener {
            val incidentsAdapter: IncidentsAdapter = rv_inputs.adapter as IncidentsAdapter
            if(incidentsAdapter.symptoms.size < getSizeOfList()){
                //Print Error message to Screen
            }else{
                for(symptom in incidentsAdapter.symptoms){

                }
            }


        }
    }

    private fun setRecyclerView() {
        val inputsAdapter = IncidentsAdapter(inputsList)
        rv_inputs.itemAnimator = DefaultItemAnimator()
        rv_inputs.adapter = inputsAdapter
    }

    private fun initData() {

        inputsList.add("First Symptom")

    }
    private fun refreshRV() {
        rv_inputs.adapter?.notifyItemInserted(getSizeOfList())
        rv_inputs.smoothScrollToPosition(getSizeOfList())
    }

    private fun getSizeOfList(): Int {
        return inputsList.size
    }

//MaterialButtonToggleGroup materialButtonToggleGroup =
//         findViewById(R.id.toggleButton);
//int buttonId = materialButtonToggleGroup.getCheckedButtonId();
//MaterialButton button = materialButtonToggleGroup.findViewById(buttonId);
}