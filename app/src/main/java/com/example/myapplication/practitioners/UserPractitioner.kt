package com.example.myapplication.practitioners

data class UserPractitioner(
    val name: String,
    val equivalent_titles: List<String>,
    val cost_per_session: String,
    val covered_providers: List<InsuranceProvider>,
    var expandable: Boolean = true)