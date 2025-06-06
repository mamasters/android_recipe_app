package com.example.recipeapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipeapplication.model.Recipe

@Database(entities = [Recipe::class], version = 4)
@TypeConverters(Converters::class)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}