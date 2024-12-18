package com.shurdev.data.converters

import androidx.room.TypeConverter
import com.shurdev.domain.models.plant.ToxicCategory

class ToxicCategoryHashSetConverter {

    @TypeConverter
    fun fromValue(value: String): HashSet<ToxicCategory> {
        if (value.isEmpty()) {
            return hashSetOf()
        }

        return value.split(SEPARATOR).map { ToxicCategory.valueOf(it) }.toHashSet()
    }

    @TypeConverter
    fun toValue(items: HashSet<ToxicCategory>): String {
        return items.joinToString(SEPARATOR) { it.name }
    }

    companion object {
        const val SEPARATOR = "|"
    }
}