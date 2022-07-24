package com.example.myapplication.practitioners

data class InsuranceProvider(
    val name: String,
    val insuredPractitioners: List<InsuredPractitioner>,
    var expandable: Boolean = true)