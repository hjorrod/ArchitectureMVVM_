package com.inditex.architecturemvvm.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveExample(example: ExampleEntity)

    @Query("SELECT * FROM examples_list")
    fun getExamples(): List<ExampleEntity>

    @Query("DELETE FROM examples_list WHERE id = :id")
    fun removeExample(id: Int)
}