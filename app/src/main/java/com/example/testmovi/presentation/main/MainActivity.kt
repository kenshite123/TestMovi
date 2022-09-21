package com.example.testmovi.presentation.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.testmovi.R
import com.example.testmovi.databinding.ActivityMainBinding
import com.example.testmovi.presentation.base.BaseActivity
import com.example.testmovi.ext.replaceFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

//        startLocationService()

        val fragment = supportFragmentManager.findFragmentByTag(MainFragment.TAG)
        if (fragment == null) {
            replaceFragment(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
        }
    }
}