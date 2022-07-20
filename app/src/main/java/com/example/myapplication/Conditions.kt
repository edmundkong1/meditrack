package com.example.myapplication

data class Conditions(
    val name: String,
    val dosage: String,
    val actions: String,
    val directions: String,
    var expandable: Boolean = true
)