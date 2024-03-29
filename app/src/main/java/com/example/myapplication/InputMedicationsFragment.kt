package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "CutPasteId")
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
            view.findViewById<EditText>(R.id.medicationTime).error = null
            view.findViewById<TextView>(R.id.days_check).error = null
            val name : String = view.findViewById<EditText?>(R.id.medName).text.toString()
            val time: String = chooseTime.text.toString()
            val monCheck: CheckBox = view.findViewById(R.id.checkMonday)
            val tuesCheck: CheckBox = view.findViewById(R.id.checkTuesday)
            val wedCheck: CheckBox = view.findViewById(R.id.checkWednesday)
            val thursCheck: CheckBox = view.findViewById(R.id.checkThursday)
            val friCheck: CheckBox = view.findViewById(R.id.checkFriday)
            val satCheck: CheckBox = view.findViewById(R.id.checkSaturday)
            val sunCheck: CheckBox = view.findViewById(R.id.checkSunday)
            val dosageString: String = view.findViewById<EditText?>(R.id.medDosage).text.toString()
            val actions: String = view.findViewById<EditText?>(R.id.medActions).text.toString()
            val directions: String = view.findViewById<EditText?>(R.id.medDirections).text.toString()
            val totalAmountString: String = view.findViewById<EditText?>(R.id.medTotal).text.toString()
            var submitError = false
            val textError = "Don't leave empty"
            if (name == "") {
                val nameText = view.findViewById<EditText>(R.id.medName)
                nameText.error = textError
                submitError = true
            }
            if (time == "") {
                val timeText = view.findViewById<EditText>(R.id.medicationTime)
                timeText.error = textError
                submitError = true
            }
            if (!monCheck.isChecked && !tuesCheck.isChecked &&
                       !wedCheck.isChecked && !thursCheck.isChecked &&
                       !friCheck.isChecked && !satCheck.isChecked &&
                       !sunCheck.isChecked) {
                val daysText = view.findViewById<TextView>(R.id.days_check)
                daysText.error = "Please select at least one day"
                submitError = true
            }
            if (dosageString == "") {
                val dosageText = view.findViewById<EditText>(R.id.medDosage)
                dosageText.error = textError
                submitError = true
            }
            if (actions == "") {
                val actionsText = view.findViewById<EditText>(R.id.medActions)
                actionsText.error = textError
                submitError = true
            }
            if (directions == "") {
                val directionsText = view.findViewById<EditText>(R.id.directions)
                directionsText.error = textError
                submitError = true
            }
            if (totalAmountString == "") {
                val totalAmountString = view.findViewById<EditText>(R.id.medTotal)
                totalAmountString.error = textError
                submitError = true
            }
            if (!submitError){
                val dosage = dosageString.toInt()
                val totalAmount = totalAmountString.toInt()
                val timeHour: Int = time.substringBefore(":").toInt()
                val timeMin: Int = time.substringAfter(":").toInt()
                val newMed = Meds(name,timeHour,timeMin,dosage,actions,directions,totalAmount)
                val fis =
                    FileInputStream(activity?.filesDir.toString() + "medications_list.meditrack")
                val ois = ObjectInputStream(fis)

                @Suppress("UNCHECKED_CAST")
                val medicationsList: Array<Array<Meds>> =
                    ois.readObject() as Array<Array<Meds>>

                val days = arrayListOf<Int>()

                if (monCheck.isChecked) {
                    val newList = medicationsList[0].toMutableList()
                    newList.add(newMed)
                    medicationsList[0] = newList.toTypedArray()
                    days.add(0)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        1,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (tuesCheck.isChecked) {
                    val newList = medicationsList[1].toMutableList()
                    newList.add(newMed)
                    medicationsList[1] = newList.toTypedArray()
                    days.add(1)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        2,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (wedCheck.isChecked) {
                    val newList = medicationsList[2].toMutableList()
                    newList.add(newMed)
                    medicationsList[2] = newList.toTypedArray()
                    days.add(2)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        3,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (thursCheck.isChecked) {
                    val newList = medicationsList[3].toMutableList()
                    newList.add(newMed)
                    medicationsList[3] = newList.toTypedArray()
                    days.add(3)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        4,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (friCheck.isChecked) {
                    val newList = medicationsList[4].toMutableList()
                    newList.add(newMed)
                    medicationsList[4] = newList.toTypedArray()
                    days.add(4)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        5,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (satCheck.isChecked) {
                    val newList = medicationsList[5].toMutableList()
                    newList.add(newMed)
                    medicationsList[5] = newList.toTypedArray()
                    days.add(5)
                    (activity as InputActivity).scheduleRepeatingNotification(
                        6,
                        newMed.timeHour!!,
                        newMed.timeMin!!,
                        "Reminder: " + newMed.messageAdapter()
                    )
                }
                if (sunCheck.isChecked) {
                    val newList = medicationsList[6].toMutableList()
                    newList.add(newMed)
                    medicationsList[6] = newList.toTypedArray()
                    days.add(6)
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

                var calcDays = newMed.totalAmount / newMed.dosage - days.size
                val calendar = Calendar.getInstance()
                val today = calendar.get(Calendar.DAY_OF_WEEK) - 1
                var index = 0
                var count = 0
                for (i in days.indices) {
                    if (days[i] > today) {
                        index = i
                        count = days[i] - today
                        calcDays--
                    }
                }

                while (calcDays > 0) {
                    val current = days[index]
                    index += 1
                    if (index >= days.size) index = 0
                    val next = days[index]

                    if (current < next) {
                        count += next - current
                    } else {
                        count += (7 - current + next)
                    }
                    calcDays--
                }
                calendar.add(Calendar.DAY_OF_MONTH, count)
                val fis2 = FileInputStream(activity?.filesDir.toString() + "refills_list.meditrack")
                val ois2 = ObjectInputStream(fis2)
                val refillsList: Array<Refills> =
                    ois2.readObject() as Array<Refills>
                val refillsmutable = refillsList.toMutableList()

                val temprefill = Refills(newMed.name + " Refill", newMed.timeHour!!, newMed.timeMin!!, newMed.totalAmount.toString(),
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))

                refillsmutable.add(Refills(newMed.name + " Refill", newMed.timeHour!!, newMed.timeMin!!, newMed.totalAmount.toString(),
                    calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)))
                val reffos = FileOutputStream(activity?.filesDir.toString() + "refills_list.meditrack")
                val refoos = ObjectOutputStream(reffos)

                (activity as InputActivity).scheduleNotification(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    newMed.timeHour!!,
                    newMed.timeMin!!,
                    "Refill: " + newMed.name + " " + temprefill.messageAdapter()
                )

                refoos.writeObject(refillsmutable.toTypedArray())
                refoos.close()
                // Below quits input tab and returns to previous tab
                activity?.finish()
            }
            else {
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