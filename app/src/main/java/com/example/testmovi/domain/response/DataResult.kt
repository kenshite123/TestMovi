package com.example.testmovi.domain.response

sealed class DataResult {
    class Success<T>(val data: T?) : DataResult()
    class Error(val e: Throwable) : DataResult()
}
