package com.example.simplescreendesigns_xml

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import com.example.simplescreendesigns_xml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnPermissions.setOnClickListener {
            val intent = Intent(this, permissions_test::class.java)
            startActivity(intent)

        }

        binding.btnLoginScreen.setOnClickListener {
            val intent = Intent(this, lgoinScreen::class.java)
            startActivity(intent)

        }

        binding.btnProfileDetailsScreen.setOnClickListener {
            val intent = Intent(this, profile_details_screen::class.java)
            startActivity(intent)

        }


        binding.btnNotification.setOnClickListener {
            val intent = Intent(this, notification_test::class.java)
            startActivity(intent)

        }

}
}