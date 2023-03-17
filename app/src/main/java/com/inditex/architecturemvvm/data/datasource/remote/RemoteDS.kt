package com.inditex.architecturemvvm.data.datasource.remote

import com.inditex.architecturemvvm.data.api.example.ExampleApiResponse
import com.inditex.architecturemvvm.data.model.ExampleDTO
import com.inditex.architecturemvvm.data.util.Result

interface RemoteDS {
    fun getExamples(count: String?): Result<ExampleApiResponse<ExampleDTO>>
}