package com.inditex.architecturemvvm.data.repository

import com.inditex.architecturemvvm.data.model.ExampleDTO
import kotlinx.coroutines.flow.Flow
import com.inditex.architecturemvvm.data.util.Result

interface ExampleApiRepository {
    fun getExamples(count: String?): Flow<Result<List<ExampleDTO>>>
}