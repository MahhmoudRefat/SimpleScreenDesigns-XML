package com.example.simplescreendesigns_xml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.data.UserData
import com.example.simplescreendesigns_xml.databinding.ActivityProfileDetailsScreenBinding

class profile_details_screen : AppCompatActivity() {
    lateinit var binding: ActivityProfileDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityProfileDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userdata = UserData("mahmoud","01116693536","Life is short, make every hair flip fabulous.")

        binding.wholeData = userdata



    }
}