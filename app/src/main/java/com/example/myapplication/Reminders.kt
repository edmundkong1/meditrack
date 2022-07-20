package com.example.myapplication

import java.io.Serializable

//objects for Reminders
open class Reminders(_name: String, _timeHour: Int, _timeMin: Int) : Serializable {
    var name: String? = _name
    var timeHour: Int? = _timeHour
    var timeMin: Int? = _timeMin
    protected open val colour: String? = null
    var expandable: Boolean? = true
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

    fun callDoctor() {}
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