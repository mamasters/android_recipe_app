package com.example.recipeapplication.data.repository

import com.example.recipeapplication.data.local.RecipeDao
import com.example.recipeapplication.model.Recipe
import jakarta.inject.Inject

class RecipeRepository @Inject constructor(private val recipeDao: RecipeDao) {

    suspend fun insert(recipe: Recipe) = recipeDao.insertRecipe(recipe)

    suspend fun getAll(): List<Recipe> = recipeDao.getAllRecipes()

    suspend fun getRecipeById(id: Int): Recipe? = recipeDao.getRecipeById(id)

    suspend fun delete(recipe: Recipe) = recipeDao.deleteRecipe(recipe)

    suspend fun deleteAll() = recipeDao.deleteAll()

    suspend fun toggleFavorite(recipe: Recipe) {
        recipeDao.updateFavoriteStatus(recipe.id, !recipe.isFavorite)
    }
}