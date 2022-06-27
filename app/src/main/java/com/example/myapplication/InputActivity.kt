package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.myapplication.databinding.ActivityInputBinding

//new Activity created because Input tab is a new screen
//TODO: need to add either the action bar at the bottom of this new screen, or a back button to go to previous screen
class InputActivity : AppCompatActivity() {
    //TODO: add all input content here, and layout info to activity_input.xml file
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}