package com.example.recipeapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.recipeapplication.data.local.Converters

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val instructions: String,
    val category: String,
    // val imageUrl: String,
    @TypeConverters(Converters::class) val ingredients: List<String>,
    val isFavorite: Boolean = false
)