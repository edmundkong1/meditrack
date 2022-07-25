package com.example.myapplication

import android.R
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.Fragment
import java.io.File
import java.util.*


class InputIncidentReportFragment : Fragment() {
//    val CAMERA_PERMISSION_CODE = 1000;
//    private val IMAGE_CAPTURE_CODE = 1001
//    private var imageUri: Uri? = null
//    private var imageView: ImageView? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_input_incident_report, container, false)
//    }
//
//    private fun requestCameraPermission(): Boolean {
//        var permissionGranted = false
//        // If system os is Marshmallow or Above, we need to request runtime permission
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
//            val cameraPermissionNotGranted = checkSelfPermission(activity as Context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
//            if (cameraPermissionNotGranted){
//                val permission = arrayOf(Manifest.permission.CAMERA)
//
//                // Display permission dialog
//                requestPermissions(permission, CAMERA_PERMISSION_CODE)
//            }
//            else{
//                // Permission already granted
//                permissionGranted = true
//            }
//        }
//        else{
//            // Android version earlier than M -> no need to request permission
//            permissionGranted = true
//        }
//
//        return permissionGranted
//    }
//
//    // Handle Allow or Deny response from the permission dialog
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        if (requestCode === CAMERA_PERMISSION_CODE) {
//            if (grantResults.size === 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                // Permission was granted
//                openCameraInterface()
//            }
//            else{
//                // Permission was denied
//                showAlert("Camera permission was denied. Unable to take a picture.");
//            }
//        }
//    }
//
//    private fun openCameraInterface() {
//        val values = ContentValues()
//        values.put(MediaStore.Images.Media.TITLE, "Take Picture")
//        values.put(MediaStore.Images.Media.DESCRIPTION, "Camera picture for sample app")
//        imageUri = activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//
//        // Create camera intent
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//
//
//        // Launch intent
//        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
//    }
//
//    fun openGallery() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
//            && checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            requestCameraPermission(
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                getString(R.string.permission_read_storage_rationale),
//                REQUEST_STORAGE_READ_ACCESS_PERMISSION
//            )
//        } else {
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            startActivityForResult(
//                Intent.createChooser(intent, getString(R.string.select_image)),
//                REQUEST_SELECT_PICTURE
//            )
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Callback from camera intent
//        if (resultCode == Activity.RESULT_OK){
//            // Set image captured to image view
//            imageView?.setImageURI(imageUri)
//        }
//        else {
//            // Failed to take picture
//            showAlert("Failed to take camera picture")
//        }
//    }
//
//    private fun showAlert(message: String) {
//        val builder = AlertDialog.Builder(activity as Context)
//        builder.setMessage(message)
//        builder.setPositiveButton("OK", null)
//        val dialog = builder.create()
//        dialog.show()
//    }
//
//
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        imageView = view.findViewById(R.id.imageview_picture)
//
//        view.findViewById<Button>(R.id.button_take_picture).setOnClickListener {
//            // Request permission
//            val permissionGranted = requestCameraPermission()
//            if (permissionGranted) {
//                // Open the camera interface
//                openCameraInterface()
//
//            }
//
//        }
//
//    }




}