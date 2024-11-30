package com.shurdev.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromJson(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toJson(list: List<String>): String {
        val json = gson.toJson(list)
        return json
    }
}