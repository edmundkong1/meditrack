package com.example.myapplication

import java.io.Serializable


class Incident : Serializable  {
    var symptom: String? = null
    var date: String? = null
    var severity: String? = null
}