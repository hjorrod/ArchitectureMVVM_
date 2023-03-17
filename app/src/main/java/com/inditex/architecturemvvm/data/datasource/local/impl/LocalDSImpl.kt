package com.inditex.architecturemvvm.data.datasource.local.impl

import com.inditex.architecturemvvm.data.dao.ExampleDao
import com.inditex.architecturemvvm.data.dao.ExampleEntity
import com.inditex.architecturemvvm.data.datasource.local.LocalDS
import javax.inject.Inject
import com.inditex.architecturemvvm.data.util.Result

class LocalDSImpl @Inject constructor(
    private val exampleDao: ExampleDao
): LocalDS {
    override fun getExamples(): Result<List<ExampleEntity>> {
        val examplesList = exampleDao.getExamples()
        return if (examplesList.isNotEmpty())
            Result.Success(examplesList)
        else
            Result.Failure()
    }

    override fun saveExamples(examples: List<ExampleEntity>) {
        examples.forEach {
            exampleDao.saveExample(it)
        }
    }

    override fun removeExample(id: Int) {
        exampleDao.removeExample(id)
    }
}