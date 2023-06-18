package com.ritikcoder.project24ofandroiddev_notificationsinandroid

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ritikcoder.project24ofandroiddev_notificationsinandroid.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    val CHANNEL_ID= "channelId"
    val CHANNEL_Name= "channel_Name"
    val notificationId= 0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        //Pending Content
        val intent= Intent(this, MainActivity3::class.java)
        val pendingIntent= PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)



        val notification= NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("App Development with Ritik")
            .setContentText("Congratulations for showing up today")
            .setSmallIcon(R.drawable.baseline_insert_emoticon_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager= NotificationManagerCompat.from(this)

        binding.imageViewForGetNotification.setOnClickListener { notificationManager.notify(notificationId, notification) }


    }

    //Method for Create notification Channel is here.
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel= NotificationChannel(CHANNEL_ID, CHANNEL_Name, NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    description= "This is my notification channel"
                    lightColor= Color.GREEN
                    enableLights(true)
                }
            val manager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

}