package com.example.myapplication

data class Inputs (
    val title: String,
    val instructions: String,
    val heading1: String,
    val heading2: String,
    val questions: List<String>,
    var expandable: Boolean = true)