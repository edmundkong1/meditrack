package com.example.myapplication

data class Appointment(
    val name: String,
    val timeHour: String,
    val timeMin: String,
    val theMonth: String,
    val year: String,
    val day: String,
    val doctor: String,
    val phoneNumber: String,
    val address: String,
    var expandable: Boolean = true)
