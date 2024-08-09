package com.example.simplescreendesigns_xml

import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.simplescreendesigns_xml.databinding.ActivityMainBinding
import com.example.simplescreendesigns_xml.databinding.ActivityPermissionsTestBinding

class permissions_test : AppCompatActivity() {
    private lateinit var binding: ActivityPermissionsTestBinding
    val READ_CONTACT_PERMISSION_REQUEST_CODE = 100
    val CAMERA_PERMISSION_REQUEST_CODE = 102
    var camerapermissionGranted = false
    var readcontactapermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionsTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updatePermissionState()


        binding.btnContact.setOnClickListener {
            if (readcontactapermissionGranted) {
                Toast.makeText(this, "Read contact permission is granted", Toast.LENGTH_SHORT)
                    .show()

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_CONTACTS),
                    READ_CONTACT_PERMISSION_REQUEST_CODE
                )
            }
        }
        binding.btnCam.setOnClickListener {
            if (camerapermissionGranted) {
                Toast.makeText(this, "camera permission is granted", Toast.LENGTH_SHORT).show()

            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_REQUEST_CODE
                )
            }
        }

    }

    private fun updatePermissionState() {
        camerapermissionGranted = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        readcontactapermissionGranted = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

        val permissionstat = when {
            camerapermissionGranted && readcontactapermissionGranted -> "Read and Camera permission are granted"
            camerapermissionGranted -> "only Camera permission are granted"
            readcontactapermissionGranted -> "only read contact permission are granted"
            else -> "no permission are granted"
        }
        binding.tvState.text = permissionstat

    }

    override fun onRequestPermissionsResult(
        requestcode: Int,
        permissions: Array<out String>, //[Mainfest.permission.read_contact]
        grantResults: IntArray //[0]
    ) {
        super.onRequestPermissionsResult(requestcode, permissions, grantResults)
        if (requestcode == CAMERA_PERMISSION_REQUEST_CODE) {
            //handel camera permission
            Toast.makeText(this, requestcode.toString(), Toast.LENGTH_SHORT).show()

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission is granted", Toast.LENGTH_SHORT).show()
                updatePermissionState()
            }
        }
        if (requestcode == READ_CONTACT_PERMISSION_REQUEST_CODE) {
            //handel read contact permissions
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "read contact  permission is granted", Toast.LENGTH_SHORT)
                    .show()
                updatePermissionState()
            }

        }
    }
}