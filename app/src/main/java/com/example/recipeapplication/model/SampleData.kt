package com.example.recipeapplication.model

val sampleRecipes = listOf(
    Recipe(
        title = "French Toast",
        author = "Alice",
        instructions = "Dip bread in eggs and cook in a pan.",
        category = "Breakfast",
        ingredients = listOf("Bread", "Eggs", "Milk", "Cinnamon")
    ),
    Recipe(
        title = "Grilled Cheese",
        author = "Bob",
        category = "Lunch",
        instructions = "Grill bread with cheese in the middle.",
        ingredients = listOf("Bread", "Cheddar Cheese", "Butter")
    ),
    Recipe(
        title = "Spaghetti Bolognese",
        author = "Mario",
        instructions = "Cook spaghetti with meat and vegetables.",
        category = "Italian",
        ingredients = listOf("Spaghetti Noodles", "Ground Beef", "Tomato Sauce", "Milk", "Cinnamon")
    ),
    Recipe(
        title = "Chocolate Brownie",
        author = "Emma",
        instructions = "Mix ingredients and bake in the oven.",
        category = "Dessert",
        ingredients = listOf("Flour", "Sugar", "Eggs", "Milk", "Chocolate")
    )
)