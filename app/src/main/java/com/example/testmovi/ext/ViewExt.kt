package com.example.testmovi.ext

import android.view.View

fun View.visibleIf(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}