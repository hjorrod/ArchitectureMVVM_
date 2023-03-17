package com.inditex.architecturemvvm.data.api.example

data class ExampleApiException (val errorMessage: String? = null) : Exception() {

    companion object {
        const val EMPTY_RESULT = "EMPTY_RESULT"
        const val UNKNOWN = "UNKNOWN"
    }
}