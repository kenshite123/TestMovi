package com.example.testmovi.domain.model

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    DONE
}

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> loading(data: T? = null): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)

        fun <T> success(data: T? = null): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T? = null, message: String? = null): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> done(data: T? = null): Resource<T> =
            Resource(status = Status.DONE, data = data, message = null)
    }
}
