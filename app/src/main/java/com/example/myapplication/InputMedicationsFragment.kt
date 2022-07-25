package com.example.myapplication

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_input_medications.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*


class InputMedicationsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_medications, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chooseTime: EditText = view.findViewById(R.id.medicationTime)
        chooseTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val currentHour = calendar[Calendar.HOUR_OF_DAY]
            val currentMinute = calendar[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(this.context,
                { timePicker, hourOfDay, minutes ->
                    chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes))},
                currentHour, currentMinute, false
            )
            timePickerDialog.show()
        }
        val submit : Button = view.findViewById(R.id.submitInputMed)
        submit.setOnClickListener{
            val name : String = view.findViewById<EditText?>(R.id.medName).text.toString()
            val time: String = chooseTime.text.toString()
            val monCheck: CheckBox = view.findViewById(R.id.checkMonday)
            val tuesCheck: CheckBox = view.findViewById(R.id.checkTuesday)
            val wedCheck: CheckBox = view.findViewById(R.id.checkWednesday)
            val thursCheck: CheckBox = view.findViewById(R.id.checkThursday)
            val friCheck: CheckBox = view.findViewById(R.id.checkFriday)
            val satCheck: CheckBox = view.findViewById(R.id.checkSaturday)
            val sunCheck: CheckBox = view.findViewById(R.id.checkSunday)
            val dosage: Int = view.findViewById<EditText?>(R.id.medDosage).text.toString().toInt()
            val actions: String = view.findViewById<EditText?>(R.id.medActions).text.toString()
            val directions: String = view.findViewById<EditText?>(R.id.medDirections).text.toString()
            val totalAmount: Int = view.findViewById<EditText?>(R.id.medTotal).text.toString().toInt()
            val timeHour: Int = time.substringBefore(":").toInt()
            val timeMin: Int = time.substringAfter(":").toInt()
            val newMed = Meds(name,timeHour,timeMin,dosage,actions,directions,totalAmount)
            val fis =
                FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
            val ois = ObjectInputStream(fis)

            @Suppress("UNCHECKED_CAST")
            val medicationsList: Array<Array<Meds>> =
                ois.readObject() as Array<Array<Meds>>

            if (monCheck.isChecked) {
                var newList = medicationsList[0].toMutableList()
                newList.add(newMed)
                medicationsList[0] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    1,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (tuesCheck.isChecked) {
                var newList = medicationsList[1].toMutableList()
                newList.add(newMed)
                medicationsList[1] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    2,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (wedCheck.isChecked) {
                var newList = medicationsList[2].toMutableList()
                newList.add(newMed)
                medicationsList[2] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    3,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (thursCheck.isChecked) {
                var newList = medicationsList[3].toMutableList()
                newList.add(newMed)
                medicationsList[3] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    4,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (friCheck.isChecked) {
                var newList = medicationsList[4].toMutableList()
                newList.add(newMed)
                medicationsList[4] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    5,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (satCheck.isChecked) {
                var newList = medicationsList[5].toMutableList()
                newList.add(newMed)
                medicationsList[5] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    6,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }
            if (sunCheck.isChecked) {
                var newList = medicationsList[6].toMutableList()
                newList.add(newMed)
                medicationsList[6] = newList.toTypedArray()
                (activity as InputActivity).scheduleRepeatingNotification(
                    7,
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Reminder: " + newMed.messageAdapter()
                )
            }

            val medfos =
                FileOutputStream(activity?.filesDir.toString() + "medications_list.meditrack")
            val medoos = ObjectOutputStream(medfos)

            medoos.writeObject(medicationsList)
            medoos.close()

            // Below quits input tab and returns to previous tab
            activity?.finish()
        }
    }

}