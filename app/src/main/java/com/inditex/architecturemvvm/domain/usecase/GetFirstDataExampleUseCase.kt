package com.inditex.architecturemvvm.domain.usecase

import com.inditex.architecturemvvm.data.repository.ExampleApiRepository
import com.inditex.architecturemvvm.data.util.Result
import com.inditex.architecturemvvm.domain.base.UseCase
import com.inditex.architecturemvvm.domain.mappers.mapToExampleBO
import com.inditex.architecturemvvm.domain.model.ExampleBO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFirstDataExampleUseCase @Inject constructor (
    private val repository: ExampleApiRepository
): UseCase<GetFirstDataExampleUseCase.Params, Result<List<ExampleBO>>>() {

    override suspend fun run(params: Params): Flow<Result<List<ExampleBO>>> {

        var result: Result<List<ExampleBO>> = Result.Failure()
        repository.getExamples(params.count).collect { response ->
            result = when (response){
                is Result.Success -> {
                    Result.Success(response.data.map { it.mapToExampleBO() })
                }
                is Result.Failure -> Result.Failure(response.error, response.exception)
            }
        }
        return flow { emit(result) }
    }

    data class Params (
        val count: String? = null
    )
}

