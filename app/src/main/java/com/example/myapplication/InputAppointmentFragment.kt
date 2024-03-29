package com.example.myapplication

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*


class InputAppointmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_appointment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //https://www.codingdemos.com/android-datepicker-button/
        val chooseDate: EditText = view.findViewById(R.id.apptDate)
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

        //https://www.codingdemos.com/android-timepicker-edittext/
        val chooseTime: EditText = view.findViewById(R.id.appointment_pick_time)
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

        val submit : Button = view.findViewById(R.id.submitInputAppt)
        submit.setOnClickListener {
            chooseDate.error = null
            chooseTime.error = null
            val name: String = view.findViewById<EditText?>(R.id.apptName).text.toString()
            val date: String = chooseDate.text.toString()
            val time: String = chooseTime.text.toString()
            val doctorname: String = view.findViewById<EditText?>(R.id.doctorName).text.toString()
            val phonenumber: String = view.findViewById<EditText?>(R.id.phoneNumber).text.toString()
            val address: String = view.findViewById<EditText?>(R.id.doctorAddress).text.toString()
            var submitError = false
            val textError = "Don't leave empty"
            if (name == "") {
                val nameText = view.findViewById<EditText>(R.id.apptName)
                nameText.error = textError
                submitError = true
            }
            if (chooseDate.text.toString() == "") {
                view.findViewById<EditText>(R.id.apptDate)
                chooseDate.error = textError
                submitError = true
            }
            if (chooseTime.text.toString() == "") {
                view.findViewById<EditText>(R.id.appointment_pick_time)
                chooseTime.error = textError
                submitError = true
            }
            if (doctorname == "") {
                val doctorText = view.findViewById<EditText>(R.id.doctorName)
                doctorText.error = textError
                submitError = true
            }
            if (phonenumber == "") {
                val phoneText = view.findViewById<EditText>(R.id.phoneNumber)
                phoneText.error = textError
                submitError = true
            }
            if (address == "") {
                val addressText = view.findViewById<EditText>(R.id.doctorAddress)
                addressText.error = textError
                submitError = true
            }
            if (!submitError){
                val timeHour: Int = time.substringBefore(":").toInt()
                val timeMin: Int = time.substringAfter(":").toInt()
                val day: Int = date.substringBefore("/").toInt()
                val month: Int = date.substringAfter("/").substringBefore("/").toInt()
                val year: Int = date.substringAfter("/").substringAfter("/").toInt()

                val newAppointment = Appointments(
                    name,
                    timeHour,
                    timeMin,
                    year,
                    month,
                    day,
                    doctorname,
                    phonenumber,
                    address
                )

                // input database into ram
                val fis =
                    FileInputStream(activity?.filesDir.toString() + "appointments_list.meditrack")
                val ois = ObjectInputStream(fis)

                @Suppress("UNCHECKED_CAST")
                var appointmentsList: Array<Appointments> =
                    ois.readObject() as Array<Appointments>

                ois.close()
                fis.close()

                val mutableAppointmentsList = appointmentsList.toMutableList()
                mutableAppointmentsList.add(newAppointment)
                appointmentsList = mutableAppointmentsList.toTypedArray()

                // enter data into database
                val apptfos =
                    FileOutputStream(activity?.filesDir.toString() + "appointments_list.meditrack")
                val apptoos = ObjectOutputStream(apptfos)
                apptoos.writeObject(appointmentsList)

                apptoos.close()
                apptfos.close()

                (activity as InputActivity).scheduleNotification(
                    newAppointment.year!!,
                    newAppointment.month!!,
                    newAppointment.day!!,
                    newAppointment.timeHour!!,
                    newAppointment.timeMin!!,
                    newAppointment.name + " " + newAppointment.messageAdapter()
                )

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