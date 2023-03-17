package com.inditex.architecturemvvm.data.api.example

import com.google.gson.annotations.SerializedName

class ExampleApiResponse<T> {
    @SerializedName("results")
    val examples: List<T> = listOf()
}