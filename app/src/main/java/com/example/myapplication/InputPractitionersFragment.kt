package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_practitioners.*
import java.util.*


class InputPractitionersFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_practitioners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val submitInputPractitioners: Button = view.findViewById(R.id.bttn_submit_input_practitioners)

        submitInputPractitioners.setOnClickListener {
            val practitionerNameET: EditText = view.findViewById(R.id.et_practitioner_name)
            val practitionerName = practitionerNameET.text.toString()

            val professionalTitleET: EditText = view.findViewById(R.id.et_title)
            val professionalTitle = professionalTitleET.text.toString()

            val costPerSessionET: EditText = view.findViewById(R.id.et_cost_per_session)
            val costPerSession = costPerSessionET.text.toString().toInt()

            val insuranceNameET: EditText = view.findViewById(R.id.insurance_name)
            val insuranceName = insuranceNameET.text.toString()

            val insuranceCoverageET: EditText = view.findViewById(R.id.insurance_coverage)
            val insuranceCoverage = insuranceCoverageET.text.toString().toInt()

            val reimbursementPercentageET: EditText = view.findViewById(R.id.reimbursement_percentage)
            val reimbursementPercentage = reimbursementPercentageET.text.toString().toInt()


            // make dropdown for insurance
            // TODO check if the entered practitioner into the list of practitioners into each of the insurances chosen

//            val insuranceCoverageString: String = insuranceCoverage.text.toString()
//            val insuranceCoverageInt : Int = insuranceCoverageString.toInt()

            // make insured practitioner object to save to db about what type of insurance info is saved
            var insuredPractitioner = InsuredPractitioner(
                professionalTitle,
                insuranceCoverage,
                reimbursementPercentage
            )

            var insuredPractitionersList = mutableListOf<InsuredPractitioner>()
            insuredPractitionersList.add(insuredPractitioner)

            // add one element list of the insuredPractitioner into a new insuranceProvider
            var insuranceProvider = InsuranceProvider(insuranceName, insuredPractitionersList)


            // make insurance provider list and pass it into user practitioner
            var insuranceProviderList = mutableListOf<InsuranceProvider>()
            insuranceProviderList.add(insuranceProvider)
            var userPractitioner = UserPractitioner(practitionerName, professionalTitle, costPerSession, insuranceProviderList)

            createAppointmentSuggestions(userPractitioner)

//            et_practitioner_name.setText(insuredPractitioner.title)
        }
    }

    fun createAppointmentSuggestions(userPractitioner: UserPractitioner) {
        
    }

}