package com.example.testmovi.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.testmovi.ext.addTo
import com.example.testmovi.ext.onDefault
import com.example.testmovi.util.RxBus
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import io.reactivex.rxjava3.disposables.CompositeDisposable

class LocationService : Service(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {
    companion object {
        const val TAG = "LocationService"
        const val LOCATION_INTERVAL: Long = 20000
        const val LOCATION_DISTANCE = 0f
        var lastLocation: Location? = null
    }

    private val compositeDisposable = CompositeDisposable()
    private var locationRequest: LocationRequest? = null
    private var googleApiClient: GoogleApiClient? = null
    override fun onCreate() {
        super.onCreate()
        buildGoogleApiClient()
        googleApiClient?.connect()
        lastLocation = Location("my_location")
        RxBus.locationPermissionObservable
            .onDefault()
            .subscribe {
                observerLocation()
            }.addTo(compositeDisposable)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (googleApiClient?.isConnected != true) {
            googleApiClient?.connect()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(baseContext)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
    }

    @SuppressLint("MissingPermission")
    override fun onConnected(bundle: Bundle?) {
        locationRequest = LocationRequest()
        locationRequest?.interval = LOCATION_INTERVAL
        locationRequest?.fastestInterval = LOCATION_INTERVAL / 2
        locationRequest?.smallestDisplacement = LOCATION_DISTANCE
        locationRequest?.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest!!)
        val locationSettingsRequest = builder.build()

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        observerLocation()
    }

    private fun observerLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // new Google API SDK v11 uses getFusedLocationProviderClient(this)
            locationRequest?.let {
                LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(
                    it, object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            locationResult.lastLocation?.let { onLocationChanged(it) }
                        }
                    },
                    Looper.myLooper()
                )
            }

            LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnCompleteListener { task: Task<Location> ->
                if (task.result != null)
                    onLocationChanged(task.result)
            }
        }
    }

    fun onLocationChanged(location: Location) {
        Log.e("onLocationChanged", location.toString())
        lastLocation?.set(location)
        //Luu lai vi tri hien tai cuoi cung
        RxBus.liveDataLocation.postValue(location)
    }

    override fun onConnectionSuspended(i: Int) {
        Log.e(TAG, "onConnectionSuspended: $i")
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.e(TAG, "onConnectionSuspended: $connectionResult")
    }
}