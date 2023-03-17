package com.inditex.architecturemvvm.data.datasource.local

import com.inditex.architecturemvvm.data.dao.ExampleEntity
import com.inditex.architecturemvvm.data.util.Result
interface LocalDS {
    fun getExamples(): Result<List<ExampleEntity>>
    fun saveExamples(examples: List<ExampleEntity>)
    fun removeExample(id: Int)
}