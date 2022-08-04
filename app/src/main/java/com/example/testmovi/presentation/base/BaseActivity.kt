package com.example.testmovi.presentation.base

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.testmovi.services.LocationService

open class BaseActivity : AppCompatActivity() {

    fun startLocationService() {
        //Kiem tra xem service nay co dang run hay khong,neu khong thi start service
        if (!isServiceRunning(this, LocationService::class.java)) {
            val intent = Intent(this, LocationService::class.java)
            startService(intent)
        }
    }

    private fun isServiceRunning(context: Context?, serviceClass: Class<*>): Boolean {
        val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, LocationService::class.java)
        stopService(intent)
    }
}