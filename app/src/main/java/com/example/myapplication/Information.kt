package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.calendar_list_item.*
import java.io.Serializable
import java.text.DateFormatSymbols

open class Information(_name: String) : Serializable {
    var name: String = _name
    var expandable: Boolean = true
    open fun aboutMeAdapter() : ArrayList<String> {
        return arrayListOf()
    }
    open fun practitionersAdapter() : ArrayList<String> {
        return arrayListOf()
    }
}

//objects for Reminders
open class Reminders(_name: String, _timeHour: Int, _timeMin: Int) : Information(_name) {
    var timeHour: Int? = _timeHour
    var timeMin: Int? = _timeMin
    protected open val colour: String? = null
    fun colourGetter(): String? {
        return colour
    }

    fun printTime(): String? {
        var ending = "am"
        var hour = timeHour
        var minute = timeMin.toString()
        if (timeHour!! == 0) {
            hour = timeHour!! + 12
        }
        if (timeHour!! >= 12) {
            if (timeHour!! >= 13) {
                hour = timeHour!! - 12
            }
            ending = "pm"
        }
        if (timeMin!! < 10) {
            minute = "0" + minute
        }

        return hour.toString() + ":" + minute + " " + ending
    }

    open fun messageAdapter(): String {
        return ""
    }

    open fun callPhone(context: Context) {}
}

//Meds class, represents the medications, and is a subclass of Reminders
class Meds(
    _name: String,
    _timeHour: Int,
    _timeMin: Int,
    _dosage: Int,
    _actions: String,
    _directions: String,
    _totalAmount: Int
) : Reminders(_name, _timeHour, _timeMin) {
    var dosage: Int = _dosage
    var actions: String? = _actions
    var directions: String? = _directions
    var totalAmount: Int = _totalAmount
    override var colour: String = "#99d4bb"

    //display text for when medication is clicked
    override fun messageAdapter(): String {
        var cardText = ""
        if (dosage != 0) {
            cardText += "Dosage: " + dosage.toString() + "mg" + "\n"
        }
        if (actions != null && actions != "") {
            cardText += "Actions: $actions\n"
        }
        if (directions != null && directions != "") {
            cardText += "Directions: $directions\n"
        }
        return cardText
    }

    override fun aboutMeAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Time: " + printTime()!!)
        newList.add("Dosage: " + dosage.toString() + "mg")
        newList.add("Actions: $actions")
        newList.add("Directions: $directions")
        newList.add("Total Amount: " + totalAmount + "mg")
        return newList
    }
}

//Meds class, represents the medications, and is a subclass of Reminders
class AboutMeMeds(
    _name: String,
    _timeHour: Int,
    _timeMin: Int,
    _dosage: Int,
    _actions: String,
    _directions: String,
    _totalAmount: Int,
    _days: String
) : Reminders(_name, _timeHour, _timeMin) {
    var dosage: Int = _dosage
    var actions: String? = _actions
    var directions: String? = _directions
    var totalAmount: Int = _totalAmount
    var days : String? = _days
    override var colour: String = "#99d4bb"

    //display text for when medication is clicked
    override fun messageAdapter(): String {
        var cardText = ""
        if(days != ""){
            cardText += "Dates: $days\n"
        }
        if (dosage != 0) {
            cardText += "Dosage: " + dosage.toString() + "mg" + "\n"
        }
        if (actions != null && actions != "") {
            cardText += "Actions: $actions\n"
        }
        if (directions != null && directions != "") {
            cardText += "Directions: $directions\n"
        }
        return cardText
    }

    override fun aboutMeAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Dates: " + days)
        newList.add("Time: " + printTime()!!)
        newList.add("Dosage: " + dosage.toString() + "mg")
        newList.add("Actions: $actions")
        newList.add("Directions: $directions")
        newList.add("Total Amount: " + totalAmount + "mg")
        return newList
    }
}

//Appointments class, represents the user's appointments, a subclass for the Reminders class
class Appointments(
    _name: String,
    _timeHour: Int,
    _timeMin: Int,
    _year: Int,
    _month: Int,
    _day: Int,
    _doctor: String,
    _phoneNumber: String,
    _address: String
) : Reminders(_name, _timeHour, _timeMin) {
    var year: Int? = _year
    var month: Int? = _month
    var day: Int? = _day
    var doctor: String? = _doctor
    var phoneNumber : String? = _phoneNumber
    var address : String? = _address
    override var colour = "#cff6d2"

    //display text for when the appointment is clicked
    override fun messageAdapter() : String {
        var cardText = ""
        if (doctor != null || doctor != "") {
            cardText += "Doctor: $doctor\n"
        }
        if (address != null || address != "") {
            cardText += "Address: $address\n"
        }
        if (phoneNumber != null || phoneNumber != "") {
            cardText += "Phone Number: $phoneNumber\n"
        }
        return cardText
    }

    override fun aboutMeAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Time: " + printTime()!!)
        val date = "Date: " + day.toString() + "/" + month.toString() + "/" + year.toString()
        newList.add(date)
        newList.add("Doctor: " + doctor!!)
        newList.add("Phone Number: " + phoneNumber!!)
        newList.add("Address: " + address!!)
        return newList
    }

    //used for calling the phone number of listed doctor for an appointment
    override fun callPhone(context: Context) {
        // Add phone call functionality
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(callIntent)
    }
}

//Refills class, represents the user's refills, a subclass of Reminders class
class Refills(
    _name: String,
    _timeHour: Int,
    _timeMin: Int,
    _amount: String,
    _year: Int,
    _month: Int,
    _day: Int
) : Reminders(_name, _timeHour, _timeMin) {
    var amount: String? = _amount
    var year: Int = _year
    var month: Int = _month
    var day: Int = _day
    override var colour = "#b0d3f7"
    override fun messageAdapter(): String {
        var cardText = ""
        if (amount != null || amount != "") {
            cardText += "Amount: "+ amount + "mg" + "\n"
        }
        return cardText
    }
}

class Symptoms(
    _name: String,
    _dosage: Int,
    _actions: String,
    _directions: String
) : Information(_name) {
    var dosage: Int = _dosage
    var actions: String = _actions
    var directions: String = _directions
    override fun aboutMeAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Dosage: $dosage")
        newList.add("Actions: $actions")
        newList.add("Directions: $directions")
        return newList
    }
}

class Conditions(
    _name: String,
    _relatedProceduresHistory: String,
    _symptomsHistory: String
) : Information(_name) {
    var relatedProceduresHistory: String = _relatedProceduresHistory
    var symptomsHistory: String = _symptomsHistory
    override fun aboutMeAdapter(): ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Related Procedures History: $relatedProceduresHistory")
        newList.add("Symptoms History: $symptomsHistory")
        return newList
    }
}

//// FOR PRACTITIONERS //////////

// eg. Manulife, list of the user's practitioners, list of practitioner info
class InsuranceProvider(
    _insuranceName: String,
    _userPractitionerList: MutableList<UserPractitioner>,
    _insuredPractitionerInfoList: MutableList<InsuredPractitionerInfo>,
): Information(_insuranceName) {
    var _insuranceName: String = _insuranceName
    var _userPractitionerList: MutableList<UserPractitioner> = _userPractitionerList
    var insuredPractitionerInfoList: MutableList<InsuredPractitionerInfo> = _insuredPractitionerInfoList
    override fun practitionersAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Insured Practitioners: $insuredPractitionerInfoList")
        return newList
    }
}

// eg. Dr. Smith, psychologist, $500 per session
class UserPractitioner(
    _name: String,
    _title: String,
    _costPerSession: Int,
    _year: Int,
    _month: Int,
    _day: Int
): Information(_name) {
    var title: String = _title
    var costPerSession: Int = _costPerSession
    override fun practitionersAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Equivalent Titles: $title")
        newList.add("Cost Per Session: $costPerSession")
        return newList
    }
}

// eg. Psychologist, $500 per year, 80%
class InsuredPractitionerInfo(
    _title: String,
    _coveredAmount: Int,
    _reimbursementPercentage: Int
): Information(_title) {
    var title: String = _title
    var coveredAmount: Int = _coveredAmount
    var reimbursement: Int = _reimbursementPercentage
    override fun practitionersAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Equivalent Titles: $title")
        newList.add("Covered Amount: $coveredAmount")
        newList.add("Reimbursement Percentage: $reimbursement%")
        return newList
    }
}

class IncidentAboutMe (
    _symptom: String,
    _date: String,
    _severity: String
): Information(_symptom)  {
    var symptom: String = _symptom
    var date: String = _date
    var severity: String = _severity
    override fun aboutMeAdapter() : ArrayList<String> {
        val newList: ArrayList<String> = arrayListOf()
        newList.add("Symptom: $symptom")
        newList.add("Date: $date")
        newList.add("Severity: $severity")
        return newList
    }
}
