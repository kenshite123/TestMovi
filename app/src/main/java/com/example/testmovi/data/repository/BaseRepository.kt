package com.example.testmovi.data.repository

import com.example.testmovi.domain.response.CommonError
import com.example.testmovi.domain.throwable.MoviThrowable
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseRepository(private val gson: Gson) {

    @Throws(Throwable::class)
    protected fun <T> networkTransform(response: Response<T?>): T {
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            } ?: run {
                val unknownError = CommonError("-1", "System went wrong!.")
                throw MoviThrowable(unknownError)
            }
        } else {
            val error: CommonError =
                gson.fromJson(response.errorBody()!!.charStream(), CommonError::class.java)
            throw MoviThrowable(error)
        }
    }
}