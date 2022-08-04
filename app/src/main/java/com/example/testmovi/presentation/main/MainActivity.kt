package com.example.testmovi.presentation.main

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.testmovi.R
import com.example.testmovi.databinding.ActivityMainBinding
import com.example.testmovi.presentation.base.BaseActivity
import com.example.testmovi.util.replaceFragment
import com.tbruyelle.rxpermissions3.RxPermissions

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        startLocationService()

        val fragment = supportFragmentManager.findFragmentByTag(MainFragment.TAG)
        if (fragment == null) {
            replaceFragment(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
        }
    }
}