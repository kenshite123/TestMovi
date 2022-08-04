package com.example.testmovi.domain.usecase

import com.example.testmovi.domain.response.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<Params, Result> {

    suspend fun execute(
        params: Params?,
        onSuccess: (DataResult.Success<Result>) -> Unit,
        onError: (DataResult.Error) -> Unit
    ) {
        try {
            val res = withContext(Dispatchers.IO) {
                loadData(params)
            }
            onSuccess.invoke(DataResult.Success(res))

        } catch (e: Exception) {
            onError.invoke(DataResult.Error(e))
        }
    }

    abstract suspend fun loadData(params: Params?): Result?

}