package com.example.myapplication

import java.io.Serializable


class Incident : Serializable  {
    lateinit var symptom: String
    lateinit var date: String
    lateinit var severity: String
}