package com.example.testmovi.domain.throwable

import com.example.testmovi.domain.response.CommonError

class MoviThrowable(error: CommonError) : Throwable(message = error.message) {
}