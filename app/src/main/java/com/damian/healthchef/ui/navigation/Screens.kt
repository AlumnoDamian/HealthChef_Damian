package com.damian.healthchef.ui.navigation

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash_screen")
    object Login: Screens("login_screen")
    object Register: Screens("register_screen")
    object Home: Screens("home_screen")

    object RecipeCategories: Screens("recipe_category_screen")
    object Recipe: Screens("recipe_list_screen")
    object RecipeDetails: Screens("recipe")
    object Upload: Screens("upload")
    object PlanificationDate: Screens("planification_date")
    object UserFeed: Screens("user_feed")
}