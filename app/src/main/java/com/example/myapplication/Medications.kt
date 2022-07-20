package com.example.myapplication

data class Medications(
    val name: String,
    val timeHour: String,
    val timeMin: String,
    val dosage: String,
    val actions: String,
    val directions: String,
    val totalAmount: String,
    var expandable: Boolean = true)



