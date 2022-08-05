package com.example.testmovi.util

import java.text.SimpleDateFormat
import java.util.*

object HumanUtil {
    fun displayDate(dt: Long?): String? {
//        val data = dt?.toLongOrNull() ?: return null
        val data = dt ?: return null
        val format = SimpleDateFormat("EEE, MM/dd/yyyy", Locale.US)
        return format.format(Date(data * 1000))
    }
}