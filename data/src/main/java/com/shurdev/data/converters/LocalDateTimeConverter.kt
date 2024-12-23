package com.shurdev.data.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun fromStringValue(value: String): LocalDateTime = LocalDateTime.parse(value)

    @TypeConverter
    fun toStringValue(value: LocalDateTime): String = value.toString()
}