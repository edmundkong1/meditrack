package com.example.myapplication

open class Reminders {
    var name: String? = null
    var time: String? = null
    protected open val color: String? = null
    var expandable: Boolean? = true
    constructor(_name: String, _time: String) {
        name = _name
        time = _time
    }
}

class Meds : Reminders {
    var dosage: String? = null
    var actions: String? = null
    var directions: String? = null
    override var color: String = "#a7fad7"
    constructor(_name: String,
                _time: String,
                _dosage: String,
                _actions: String,
                _directions: String) : super(_name, _time) {
                    dosage = _dosage
                    actions = _actions
                    directions = _directions
                }
}

class Appointments: Reminders {
    var doctor: String? = null
    var phoneNumber : String? = null
    var address : String? = null
    override var color = "#f0faa7"
    constructor(_name: String,
                _time: String,
                _doctor: String,
                _phoneNumber: String,
                _address: String) : super(_name, _time) {
        doctor = _doctor
        phoneNumber = _phoneNumber
        address = _address
    }

    fun callDoctor() {}
}

class Refills: Reminders {
    var amount: String? = null
    override var color = "#b0d3f7"
    constructor(_name: String,
                _time: String,
                _amount: String) : super(_name, _time) {
        amount = _amount
    }
}