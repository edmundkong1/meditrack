package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class InputPractitionersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_practitioners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val submitInputPractitioners: Button = view.findViewById(R.id.bttn_submit_input_practitioners)

        //what happens on submit button
        submitInputPractitioners.setOnClickListener {
            // get information from EditText fields
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

            var appointmentsLeft = "Invalid - Entered Cost per Session above annual coverage!"

            // we get the appointments left in this year
            if (costPerSession <= insuranceCoverage) {
                // will auto round-down to nearest int
                val apptLeft: Int = insuranceCoverage/costPerSession
                appointmentsLeft = apptLeft.toString()
            }

            // get amount covered
            val outOfPocketCost: Float = (costPerSession - (costPerSession * reimbursementPercentage / 100)).toFloat()
            // make practitioner object with entered data
            var practitioner = Practitioner(practitionerName, professionalTitle, costPerSession, insuranceName,
                insuranceCoverage, reimbursementPercentage, appointmentsLeft, outOfPocketCost)

            // open saved insurance providers from database
            // input database into ram
            val fis =
                FileInputStream(activity?.filesDir.toString() + "practitioners_list.meditrack")
            val ois = ObjectInputStream(fis)

            @Suppress("UNCHECKED_CAST")
            var practitionerList: Array<Practitioner> =
                ois.readObject() as Array<Practitioner>

            // create data
            val mutablePractitionerList = practitionerList.toMutableList()
            mutablePractitionerList.add(practitioner)
            practitionerList = mutablePractitionerList.toTypedArray()

            // enter data into database
            val insfos =
                FileOutputStream(activity?.filesDir.toString() + "practitioners_list.meditrack")
            val insoos = ObjectOutputStream(insfos)
            insoos.writeObject(practitionerList)
            insoos.close()

            createAppointmentSuggestions()
            activity?.finish()
        }
    }

    fun createAppointmentSuggestions() {}

}