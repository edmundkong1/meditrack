package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
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
import java.util.*


class InputConditionsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val submit : Button = view.findViewById(R.id.submitInputConditions)
        submit.setOnClickListener {
            val conditionName: String = view.findViewById<EditText?>(R.id.conditionsName).text.toString()
            val procedureHistory: String = view.findViewById<EditText?>(R.id.conditionsProcedureHistory).text.toString()
            val symptomsHistory: String = view.findViewById<EditText?>(R.id.conditionsSymptomsHistory).text.toString()
            var submitError = false
            val textError = "Don't leave empty"
            if (conditionName == "") {
                view.findViewById<EditText?>(R.id.conditionsName).error = textError
                submitError = true
            }
            if (procedureHistory == "") {
                view.findViewById<EditText?>(R.id.conditionsProcedureHistory).error = textError
                submitError = true
            }
            if (symptomsHistory == "") {
                view.findViewById<EditText?>(R.id.conditionsSymptomsHistory).error = textError
                submitError = true
            }
            if (!submitError) {

                val newCondition = Conditions(
                    conditionName,
                    procedureHistory,
                    symptomsHistory
                )

                val fis =
                    FileInputStream(activity?.filesDir.toString() + "conditions_list.meditrack")
                val ois = ObjectInputStream(fis)

                @Suppress("UNCHECKED_CAST")
                var conditionsList: Array<Conditions> =
                    ois.readObject() as Array<Conditions>

                val mutableConditionsList = conditionsList.toMutableList()
                mutableConditionsList.add(newCondition)
                conditionsList = mutableConditionsList.toTypedArray()
                //appointmentsList = emptyArray()

                val apptfos =
                    FileOutputStream(activity?.filesDir.toString() + "conditions_list.meditrack")
                val apptoos = ObjectOutputStream(apptfos)
                apptoos.writeObject(conditionsList)
                apptoos.close()

                // Below quits input tab and returns to previous tab
                activity?.finish()
            } else {
                val dialogBuilder = AlertDialog.Builder(context)

                // set message of alert dialog
                dialogBuilder.setMessage("Please don't leave any fields empty")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Okay", DialogInterface.OnClickListener {
                            dialog, id -> dialog.dismiss()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Empty Fields")
                // show alert dialog
                alert.show()
            }
        }

    }

}