package com.example.recipeapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapplication.data.repository.RecipeRepository
import com.example.recipeapplication.model.Recipe
import com.example.recipeapplication.model.sampleRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var recipes by mutableStateOf<List<Recipe>>(emptyList())
        private set

    init {
        loadRecipes()
        preloadSampleDataIfEmpty()
    }

    private fun preloadSampleDataIfEmpty() {
        viewModelScope.launch {
            if (repository.getAll().isEmpty()) {
                sampleRecipes.forEach { recipe ->
                    repository.insert(recipe)
                }
                loadRecipes()
            }
        }
    }

    fun loadRecipes() {
        viewModelScope.launch {
            recipes = repository.getAll()
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insert(recipe)
            loadRecipes()
        }
    }

    fun clearRecipes() {
        viewModelScope.launch {
            repository.deleteAll()
            loadRecipes()
        }
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            repository.toggleFavorite(recipe)
            loadRecipes()
        }
    }

    var searchQuery by mutableStateOf("")
        private set

    val filteredRecipes: List<Recipe>
        get() = if (searchQuery.isBlank()) {
            recipes
        } else {
            recipes.filter {
                it.title.contains(searchQuery, ignoreCase = true) ||
                        it.author.contains(searchQuery, ignoreCase = true)
            }
        }

    fun onSearchQueryChanged(newQuery: String) {
        searchQuery = newQuery
    }
}