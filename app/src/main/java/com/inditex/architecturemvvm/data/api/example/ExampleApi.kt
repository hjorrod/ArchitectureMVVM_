package com.inditex.architecturemvvm.data.api.example

import com.inditex.architecturemvvm.data.model.ExampleDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleApi {
    @GET("examples/example/info/{count}")
    fun getExamples(
        @Path("count") count: String? = null
    ): Call<ExampleApiResponse<ExampleDTO>>//Response<ExampleApiResponse<ExampleDTO>>
}