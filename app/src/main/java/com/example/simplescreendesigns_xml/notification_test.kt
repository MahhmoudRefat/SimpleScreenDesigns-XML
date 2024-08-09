package com.example.simplescreendesigns_xml

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.simplescreendesigns_xml.databinding.ActivityNotificationTestBinding
import com.example.simplescreendesigns_xml.databinding.ActivityProfileDetailsScreenBinding

class notification_test : AppCompatActivity() {
    lateinit var binding: ActivityNotificationTestBinding
    val requestNotificationKey = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val channelId = "my_channel_id"

        val notficationManager = NotificationManagerCompat.from(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannelCompat.Builder(channelId, NotificationManager.IMPORTANCE_HIGH)
                    .setName("Message channel ")
                    .setDescription("used to notify about user message ")
                    .build()
            notficationManager.createNotificationChannel(channel)

        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("This is a notifaction ")
            .setContentText("this is a notification text ")
            .setSmallIcon(R.drawable.phone_call)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        binding.btnNotification.setOnClickListener {
            chekingNotification {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return@chekingNotification
                }
                notficationManager.notify(0, notification)
            }
        }
    }

    private fun chekingNotification(startNotification: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    requestNotificationKey
                )
                return
            }
        }
        startNotification.invoke()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestNotificationKey) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "notification permission granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}