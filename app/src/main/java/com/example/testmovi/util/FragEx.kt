package com.example.testmovi.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(viewId: Int, fragment: Fragment, tag: String) {
    val manager = supportFragmentManager.beginTransaction()
    manager.replace(viewId, fragment, tag)
    manager.commit()
}