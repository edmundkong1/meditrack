package com.example.myapplication

import java.io.Serializable

//objects for Reminders
open class Reminders(_name: String, _time: String) : Serializable {
    var name: String? = _name
    var time: String? = _time
    protected open val color: String? = null
    var expandable: Boolean? = true

    open fun messageAdapter(): String {
        return ""
    }
}

//Meds class, represents the medications, and is a subclass of Reminders
class Meds(
    _name: String,
    _time: String,
    _dosage: Int,
    _actions: String,
    _directions: String,
    _totalAmount: Int
) : Reminders(_name, _time) {
    var dosage: Int = _dosage
    var actions: String? = _actions
    var directions: String? = _directions
    var totalAmount: Int = _totalAmount
    override var color: String = "#a7fad7"

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
    _time: String,
    _year: Int,
    _month: Int,
    _day: Int,
    _doctor: String,
    _phoneNumber: String,
    _address: String
) : Reminders(_name, _time) {
    var year: Int? = _year
    var month: Int? = _month
    var day: Int? = _day
    var doctor: String? = _doctor
    var phoneNumber : String? = _phoneNumber
    var address : String? = _address
    override var color = "#f0faa7"

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
class Refills(_name: String, _time: String, _amount: String) : Reminders(_name, _time) {
    var amount: String? = _amount
    override var color = "#b0d3f7"
}