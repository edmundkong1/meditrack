package com.example.myapplication

data class Medications(
    val name: String,
    val timeHour: Int,
    val timeMin: Int,
    val dosage: String,
    val actions: String,
    val directions: String,
    val totalAmount: Int,
    var expandable: Boolean = true)



