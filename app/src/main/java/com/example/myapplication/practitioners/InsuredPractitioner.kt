package com.example.myapplication.practitioners

data class InsuredPractitioner(
    val equivalent_titles: List<String>, // all possible names of this practitioner with this coverage
    val coveredAmount: Number,
    val reimbursement_percentage: Int
    )