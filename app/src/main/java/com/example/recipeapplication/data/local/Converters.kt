package com.example.recipeapplication.data.local

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(separator = "!")
    }

    @TypeConverter
    fun toList(string: String): List<String> {
        return if (string.isEmpty()) emptyList() else string.split("!")
    }
}