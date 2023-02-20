package com.yotfr.owes.data.datasource.local

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateTypeConverter {

    @TypeConverter
    fun toLocalDate(localDateString: String?): LocalDate? {
        localDateString?.let {
            return LocalDate.parse(localDateString)
        } ?: kotlin.run {
            return null
        }
    }

    @TypeConverter
    fun toLocalDateString(localDate: LocalDate?): String? {
        localDate?.let {
            return localDate.toString()
        } ?: kotlin.run {
            return null
        }
    }

}