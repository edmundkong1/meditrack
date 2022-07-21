package com.example.myapplication

data class Conditions(
    val name: String,
    val relatedProceduresHistory: String,
    val symptomsHistory: String,
    var expandable: Boolean = true
)