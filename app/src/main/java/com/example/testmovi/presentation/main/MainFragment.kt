package com.example.testmovi.presentation.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.example.testmovi.R
import com.example.testmovi.databinding.FragmentMainBinding
import com.example.testmovi.domain.model.ForecastDaily
import com.example.testmovi.domain.response.DataResult
import com.example.testmovi.ext.addTo
import com.example.testmovi.ext.onDefault
import com.example.testmovi.ext.visibleIf
import com.example.testmovi.presentation.adapter.ForecastAdapter
import com.example.testmovi.presentation.base.BaseFragment
import com.example.testmovi.util.ConnectivityLiveData
import com.example.testmovi.util.RxBus
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.jakewharton.rxbinding4.view.visibility
import com.jakewharton.rxbinding4.widget.textChanges
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import java.util.concurrent.TimeUnit

class MainFragment : BaseFragment(), KoinComponent {

    companion object {
        const val TAG = "MainFragment"

        fun newInstance(): MainFragment = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ForecastAdapter
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObserve()
        checkPermissionLocation()
    }

    private fun initViews() {
        adapter = ForecastAdapter()
        binding.rvData.adapter = adapter
    }

    private fun initObserve() {
        ConnectivityLiveData(requireContext()).observe(viewLifecycleOwner) {}

        binding.edSearch.textChanges().debounce(500, TimeUnit.MILLISECONDS)
            .onDefault()
            .filter { it.length > 3 }
            .subscribe({
                showContent(loading = true)
                mainViewModel.searchWeather(it.toString())
            }, {
                Log.e(TAG, it.localizedMessage ?: "null")
            })

        RxBus.liveDataLocation.observe(viewLifecycleOwner) {
            val lat = it.latitude
            val lng = it.longitude
            Log.d(TAG, "lat: $lat, lng:$lng")
            if (binding.edSearch.text.toString().isNullOrEmpty()) {
                showContent(loading = true)
                mainViewModel.getForecastDaily(lat, lng)
            }
        }

        mainViewModel.dataForecast.observe(viewLifecycleOwner) {
            when (it) {
                is DataResult.Success<*> -> {
                    showContent(content = true)
                    adapter.notifyData((it.data as? ForecastDaily)?.list.orEmpty())
                }
                is DataResult.Error -> {
                    showContent(error = true)
                    binding.error.text = it.e.localizedMessage.toString()
                }
            }
        }
    }

    private fun showContent(
        loading: Boolean = false,
        content: Boolean = false,
        error: Boolean = false
    ) {
        binding.rvData.visibleIf(content)
        binding.loading.visibleIf(loading)
        binding.error.visibleIf(error)
    }

    private fun checkPermissionLocation() {
        if (isLocationPermissionGranted()) {
            checkGpsAndGetLocation()
        } else {
            RxPermissions(this)
                .request(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                .onDefault()
                .subscribe { grand ->
                    if (!grand) {
                        // show error
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:${requireActivity().packageName}")
                        ).apply {
                            addCategory(Intent.CATEGORY_DEFAULT)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(this)
                        }
                    } else {
                        checkGpsAndGetLocation()
                    }
                }.addTo(compositeSubscription)
        }
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkGpsAndGetLocation() {
        val manager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (manager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == false) {
            turnGPSOn()
        } else {
            RxBus.locationPermissionObservable.onNext(true)
        }
    }

    private fun turnGPSOn() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(requireActivity())
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())
        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        requireActivity(),
                        10
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }
}