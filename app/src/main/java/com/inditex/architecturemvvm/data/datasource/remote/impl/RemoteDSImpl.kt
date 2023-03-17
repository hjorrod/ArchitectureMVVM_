package com.inditex.architecturemvvm.data.datasource.remote.impl

import com.inditex.architecturemvvm.data.api.example.ExampleApi
import com.inditex.architecturemvvm.data.api.example.ExampleApiException
import com.inditex.architecturemvvm.data.api.example.ExampleApiResponse
import com.inditex.architecturemvvm.data.datasource.remote.RemoteDS
import com.inditex.architecturemvvm.data.model.ExampleDTO
import com.inditex.architecturemvvm.data.util.Result
import com.inditex.architecturemvvm.data.util.ServiceError
import com.inditex.architecturemvvm.data.util.ServiceErrorInfo
import javax.inject.Inject

class RemoteDSImpl @Inject constructor(
    private val exampleApi: ExampleApi
): RemoteDS {
    override fun getExamples(count: String?): Result<ExampleApiResponse<ExampleDTO>> {
        val response = exampleApi.getExamples(count).execute()
        if (response.isSuccessful) {
            response.body()?.let { apiResponse ->
                return if (apiResponse.examples.isEmpty())
                    Result.Failure(exception = ExampleApiException(ExampleApiException.EMPTY_RESULT))
                else
                    Result.Success(apiResponse)
            } ?: kotlin.run {
                return Result.Failure(exception = ExampleApiException(ExampleApiException.EMPTY_RESULT))
            }
        }
        return Result.Failure(
            ServiceError(ServiceErrorInfo("Fail to get examples")),
            ExampleApiException(ExampleApiException.UNKNOWN)
        )
    }
}