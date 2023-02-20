package com.yotfr.owes.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        DebtEntity::class,
        PersonEntity::class
    ],
    version = 1
)
@TypeConverters(LocalDateTypeConverter::class)
abstract class DebtDatabase : RoomDatabase() {
    abstract val debtDao: DebtDao
    abstract val personDao: PersonDao
}
