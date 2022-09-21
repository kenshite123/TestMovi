package com.example.testmovi.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class PrefUtilImpl(context: Context) : PrefUtilService {
    var prefs: SharedPreferences
    val MY_PREFS = "MyAppPrefs"

    init {
        prefs = context.getSharedPreferences(
            MY_PREFS, Context.MODE_PRIVATE
        )
    }

    override fun setObject(name: String, obj: Any) {
        try {
            val prefsEditor = prefs.edit()
            val gson = Gson()
            val json = gson.toJson(obj)
            prefsEditor.putString(name, json)
            prefsEditor.apply()
        } catch (e: Exception) {
        }
    }

    override fun <T : Any> getObject(name: String, type: Class<T>): T? {
        try {
            val gson = Gson()
            val json = prefs.getString(name, "")

            return gson.fromJson(json, type)
        } catch (e: Exception) {
        }

        return null
    }

    override fun setBoolean(title: String, boole: Boolean) {
        prefs.edit().putBoolean(title, boole).apply()
    }

    override fun getBoolean(title: String): Boolean {
        var result = false
        try {
            result = prefs.getBoolean(title, false)
        } catch (e: Exception) {

        }

        return result
    }

    override fun setString(title: String, str: String) {
        prefs.edit().putString(title, str).apply()
    }

    override fun getString(title: String): String {
        return prefs.getString(title, "").toString()
    }

    override fun setInt(title: String, value: Int) {
        prefs.edit().putInt(title, value).apply()
    }

    override fun getInt(title: String): Int {
        return prefs.getInt(title, -1)
    }

    override fun setLong(title: String, value: Long) {
        prefs.edit().putLong(title, value).apply()
    }

    override fun getLong(title: String): Long {
        return prefs.getLong(title, -1)
    }

    override fun remove(title: String) {
        prefs.edit().remove(title).apply()
    }
}