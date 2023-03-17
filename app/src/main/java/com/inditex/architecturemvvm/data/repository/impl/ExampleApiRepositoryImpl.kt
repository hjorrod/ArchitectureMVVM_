package com.inditex.architecturemvvm.data.repository.impl

import com.inditex.architecturemvvm.data.datasource.local.LocalDS
import com.inditex.architecturemvvm.data.datasource.remote.RemoteDS
import com.inditex.architecturemvvm.data.model.ExampleDTO
import com.inditex.architecturemvvm.data.repository.ExampleApiRepository
import com.inditex.architecturemvvm.data.util.Result
import com.inditex.architecturemvvm.domain.mappers.mapToExampleDTO
import com.inditex.architecturemvvm.domain.mappers.mapToExampleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExampleApiRepositoryImpl @Inject constructor(
    private val local: LocalDS,
    private val remote: RemoteDS
) : ExampleApiRepository {

    override fun getExamples(count: String?): Flow<Result<List<ExampleDTO>>> = flow {
         when (val localResponse = local.getExamples()) {
            is Result.Success ->
                emit(Result.Success(localResponse.data.map { it.mapToExampleDTO() }))

            is Result.Failure -> when (val remoteResponse = remote.getExamples(count)) {
                is Result.Success -> {
                    remoteResponse.data.examples.also { examplesList ->
                        local.saveExamples(examplesList.map { it.mapToExampleEntity() })
                        emit(Result.Success(examplesList))
                    }
                }
                is Result.Failure ->
                    emit(Result.Failure(remoteResponse.error, remoteResponse.exception))
            }
        }
    }
}