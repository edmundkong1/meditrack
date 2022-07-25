package com.example.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.fragment_input_incident_report.*
import java.util.*

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.net.Uri

import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.checkSelfPermission

class InputIncidentReportFragment : Fragment() {
    private var inputsList = ArrayList<String>()

    private var incidentAdapter: IncidentsAdapter? = null
    val CAMERA_PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    private var imageUri: Uri? = null
    private var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //incidentAdapter = IncidentsAdapter(inputsList)
        //rv_inputs.adapter = IncidentsAdapter(inputsList)
//        incidentAdapter = .setWhenClickListener(new IncidentsAdpater.OnItemsClickListener() {
//            override fun OnToggleButtonClick(spinnerValue: String) {
//                algorithmTitleText.setText(rvOneModel.getName())
//                setRVTwoList(rvOneModel.getNum())
//            }
//        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input_incident_report, container, false)
    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    imageView = view.findViewById(R.id.imageview_picture)

    view.findViewById<Button>(R.id.button_take_picture).setOnClickListener {
        // Request permission
        val permissionGranted = requestCameraPermission()
        if (permissionGranted) {
            // Open the camera interface
            openCameraInterface()

        }

    }
        initData()
        setRecyclerView()
        val chooseDate: EditText = view.findViewById(R.id.incidentDate)
        chooseDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
            val datePickerDialog = this.context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { datePicker, year, month, day -> chooseDate.setText(day.toString() + "/" + (month + 1) + "/" + year)}, year, month, dayOfMonth
                )
            }
            if (datePickerDialog != null) {
                datePickerDialog.datePicker.minDate = System.currentTimeMillis()
                datePickerDialog.show()
            }
        }
        val addRowBtn: Button = view.findViewById(R.id.add_row_btn)
        addRowBtn.setOnClickListener {
            val symptoms  = resources.getStringArray(R.array.symptoms_array)
            if(getSizeOfList() < symptoms.size) {
                inputsList.add("New Symptom");
                refreshRV()
            }else{
                addRowBtn.visibility = View.INVISIBLE
            }
        }
        val submitIncidents: Button = view.findViewById(R.id.submitInputIncident)
        submitIncidents.setOnClickListener {
            val incidentsAdapter: IncidentsAdapter = rv_inputs.adapter as IncidentsAdapter
            if(incidentsAdapter.symptoms.size < getSizeOfList()){
                //Print Error message to Screen
            }else{
                for(symptom in incidentsAdapter.symptoms){

                }
            }


        }
    }

    private fun setRecyclerView() {
        val inputsAdapter = IncidentsAdapter(inputsList)
        rv_inputs.itemAnimator = DefaultItemAnimator()
        rv_inputs.adapter = inputsAdapter
    }

    private fun initData() {

        inputsList.add("First Symptom")

    }
    private fun refreshRV() {
        rv_inputs.adapter?.notifyItemInserted(getSizeOfList())
        rv_inputs.smoothScrollToPosition(getSizeOfList())
    }

    private fun getSizeOfList(): Int {
        return inputsList.size
    }

//MaterialButtonToggleGroup materialButtonToggleGroup =
//         findViewById(R.id.toggleButton);
//int buttonId = materialButtonToggleGroup.getCheckedButtonId();
//MaterialButton button = materialButtonToggleGroup.findViewById(buttonId);

    private fun requestCameraPermission(): Boolean {
        var permissionGranted = false
        // If system os is Marshmallow or Above, we need to request runtime permission
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            val cameraPermissionNotGranted = checkSelfPermission(activity as Context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
            if (cameraPermissionNotGranted){
                val permission = arrayOf(Manifest.permission.CAMERA)

                // Display permission dialog
                requestPermissions(permission, CAMERA_PERMISSION_CODE)
            }
            else{
                // Permission already granted
                permissionGranted = true
            }
        }
        else{
            // Android version earlier than M -> no need to request permission
            permissionGranted = true
        }

        return permissionGranted
    }

    // Handle Allow or Deny response from the permission dialog
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode === CAMERA_PERMISSION_CODE) {
            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Permission was granted
                openCameraInterface()
            }
            else{
                // Permission was denied
                showAlert("Camera permission was denied. Unable to take a picture.");
            }
        }
    }

    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Take Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Camera picture for sample app")
        imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        // Create camera intent
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        // Launch intent
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Callback from camera intent
        if (resultCode == Activity.RESULT_OK){
            // Set image captured to image view
            imageView?.setImageURI(imageUri)
        }
        else {
            // Failed to take picture
            showAlert("Failed to take camera picture")
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(activity as Context)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }
}