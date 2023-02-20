package com.yotfr.owes.data.datasource.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "person",
    indices = [Index(value = ["name"], unique = true)]
)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val name: String,
    val phoneNumber: String?
)
