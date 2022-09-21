package com.example.testmovi.data.prefs

/**
 * @author BCU2HC
 * Created 9/21/2022 at 2:48 PM
 */
interface PrefUtilService {
    fun setObject(name: String, obj: Any)
    fun <T : Any> getObject(name: String, type: Class<T>): T?
    fun setBoolean(title: String, boole: Boolean)
    fun getBoolean(title: String): Boolean
    fun setString(title: String, str: String)
    fun getString(title: String): String
    fun setInt(title: String, value: Int)
    fun getInt(title: String): Int
    fun setLong(title: String, value: Long)
    fun getLong(title: String): Long
    fun remove(title: String)
}