package com.example.recipeapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapplication.model.Recipe
import com.example.recipeapplication.viewmodel.RecipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(viewModel: RecipeViewModel = hiltViewModel()) {
    val recipes = viewModel.filteredRecipes
    val query = viewModel.searchQuery

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recipe Book") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            Text("Recipe List", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            SearchBar(
                query = query,
                onQueryChanged = viewModel::onSearchQueryChanged
            )

            LazyColumn {
                items(items = recipes, key = { it.id }) { recipe: Recipe ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            // Title Row with Favorite Icon
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(recipe.title, style = MaterialTheme.typography.titleMedium)

                                IconButton(onClick = { viewModel.toggleFavorite(recipe) }) {
                                    Icon(
                                        imageVector = if (recipe.isFavorite)
                                            Icons.Default.Favorite
                                        else
                                            Icons.Default.FavoriteBorder,
                                        contentDescription = "Toggle Favorite",
                                        tint = if (recipe.isFavorite) Color.Red else Color.Gray
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text("By ${recipe.author}", style = MaterialTheme.typography.labelSmall)

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = recipe.category,
                                modifier = Modifier
                                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Ingredients:", style = MaterialTheme.typography.labelMedium)
                            recipe.ingredients.forEach { ingredient ->
                                Text("- $ingredient", style = MaterialTheme.typography.bodySmall)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Instructions:")
                            Text(
                                recipe.instructions,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}
