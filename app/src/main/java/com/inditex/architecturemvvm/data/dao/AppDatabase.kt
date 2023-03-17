package com.inditex.architecturemvvm.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ExampleEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}